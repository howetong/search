package cn.howe.search;

import cn.howe.search.suggest.SuggestMeta;
import cn.howe.search.suggest.SuggestRequest;
import cn.howe.search.suggest.Suggester;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.WildcardQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class SearchDemo {

    private static String INDEX_PATH = "/data/soft/search/index";
    private static String FILE_PATH = "/data/soft/search/demo.txt";



    private static List<SuggestMeta> getSugMetas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            List<SuggestMeta> sugMetas = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length == 8) {
                    String word = tokens[1];
                    double weight = Double.parseDouble(tokens[2]);
                    String id = tokens[3];
                    JSONObject json = new JSONObject();
                    json.put("origin_word", word);
                    json.put("type", Integer.parseInt(tokens[4]));
                    json.put("sub_type", Integer.parseInt(tokens[5]));
                    SuggestMeta meta = new SuggestMeta(word, weight);
                    meta.setFilterFields(ImmutableList.copyOf(tokens[7].split(" ")));
                    meta.setId(id);
                    meta.setStoredData(json.toJSONString());
                    sugMetas.add(meta);
                }
            }
            return sugMetas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        try {
            Suggester suggester = new Suggester();
            //suggester.trySwapIndexes(INDEX_PATH, getSugMetas());
            WildcardQuery query = new WildcardQuery(new Term("name", "*新世界*"));
            SuggestRequest suggestRequest = new SuggestRequest(query);
            suggestRequest.setIndexPath(INDEX_PATH);
            suggester.search(suggestRequest, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
