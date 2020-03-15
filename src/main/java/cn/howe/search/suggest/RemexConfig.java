package cn.howe.search.suggest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class RemexConfig {

    private Analyzer indexAnalyzerInstance;

    public IndexWriterConfig createIndexWriteConfig() {
        return new IndexWriterConfig(this.getIndexAnalyzerInstance());
    }

    public Analyzer getIndexAnalyzerInstance() {
        if (this.indexAnalyzerInstance != null) {
            return this.indexAnalyzerInstance;
        } else {
            synchronized (this) {
                if (this.indexAnalyzerInstance == null) {
                    this.indexAnalyzerInstance = new IKAnalyzer();
                }
            }
            return indexAnalyzerInstance;
        }
    }
}
