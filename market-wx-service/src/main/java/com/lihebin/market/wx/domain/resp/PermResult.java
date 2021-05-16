package com.lihebin.market.wx.domain.resp;

import java.util.List;

public class PermResult {
    private String id;
    private String label;
    private String api;
    private List<PermResult> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getApi() {
        return api;
    }

    public List<PermResult> getChildren() {
        return children;
    }

    public void setChildren(List<PermResult> children) {
        this.children = children;
    }

}
