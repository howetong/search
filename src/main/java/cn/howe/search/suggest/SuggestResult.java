package cn.howe.search.suggest;

import java.util.List;
import java.util.Map;

public class SuggestResult {

    private SuggestRequest request;
    private List<SuggestMeta> suggestions;
    private boolean success;
    private Map<String,String> scoredSuggestions;
    private String message;
    private long hits;
}
