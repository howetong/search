package cn.howe.search.suggest;

import org.apache.lucene.search.Query;

public class SuggestRequest {
    private Query query;
    private String indexPath;

    public SuggestRequest() {
    }

    public SuggestRequest(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }
}
