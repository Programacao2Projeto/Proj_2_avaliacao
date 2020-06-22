package com.trainingcenter.api;

import java.util.List;
import java.util.ArrayList;

public class YTTrends {
    private String term;
    private List<Youtube> yt;

    public YTTrends(){
        this.yt= new ArrayList();
    }
    public YTTrends(String term, List<Youtube> data) {
        this.term = term;
        this.yt = data;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<Youtube> getYt() {
        return yt;
    }

    public void setYt(List<Youtube> yt) {
        this.yt = yt == null ? new ArrayList() : yt;
    }
}

