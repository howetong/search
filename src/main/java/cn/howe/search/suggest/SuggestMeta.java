package cn.howe.search.suggest;

import java.util.List;

public class SuggestMeta {
    private String word;
    private double weight;
    private String id;
    private String tword;
    private String storedData;
    private List<String> filterFields;

    public SuggestMeta() {

    }

    public SuggestMeta(String word, double weight) {
        this();
        this.word = word;
        this.weight = weight;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTword() {
        return tword;
    }

    public void setTword(String tword) {
        this.tword = tword;
    }

    public String getStoredData() {
        return storedData;
    }

    public void setStoredData(String storedData) {
        this.storedData = storedData;
    }

    public List<String> getFilterFields() {
        return filterFields;
    }

    public void setFilterFields(List<String> filterFields) {
        this.filterFields = filterFields;
    }
}
