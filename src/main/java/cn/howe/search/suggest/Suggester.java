package cn.howe.search.suggest;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Suggester {

    private RemexConfig config = new RemexConfig();

    private void index(String indexPath, List<SuggestMeta> suggestMetaList) throws IOException {

        // 指定索引位置
        Directory directory = FSDirectory.open(Paths.get(indexPath));


        // 写索引配置
        //IndexWriterConfig config = this.config.createIndexWriteConfig();
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE); // 创建索引的方式，每次都新建
        // IndexWriter是lucene的核心类，用于存储索引
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 写入索引
        indexDocs(indexWriter, suggestMetaList);

        indexWriter.close();
    }

    private void indexDocs(IndexWriter indexWriter, List<SuggestMeta> metaList) throws IOException {
        for (SuggestMeta suggestMeta : metaList) {
            Document document = new Document();
            Field id = new StringField("id", suggestMeta.getId(), Field.Store.YES);
            Field weight = new DoublePoint("weight", suggestMeta.getWeight());
            Field title = new StringField("name", suggestMeta.getWord(), Field.Store.YES);
            document.add(id);
            document.add(weight);
            document.add(title);
            indexWriter.addDocument(document);
        }
        System.out.println("index created");
    }

    public void trySwapIndexes(String indexPath, List<SuggestMeta> metaList) throws IOException {
        if (metaList == null) {
            return;
        }
        this.index(indexPath, metaList);
    }

    public void search(SuggestRequest suggestRequest, int maxResult) throws IOException {
        Directory directory = FSDirectory.open(Paths.get(suggestRequest.getIndexPath()));
        // 索引读取
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        TopDocs topDocs = indexSearcher.search(suggestRequest.getQuery(), maxResult);
        TotalHits totalHits = topDocs.totalHits;
        System.out.println("结果命中：" + totalHits.value);
        // 得分文档数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = directoryReader.document(docId);
            System.out.println("id: " + document.get("id"));
            System.out.println("name: " + document.get("name"));
            System.out.println("Score: " + scoreDoc.score);
            System.out.println("***********************");
        }
    }
}
