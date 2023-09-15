package com.east.demo.persist.entity;

import lombok.Data;

@Data
public class LyUserOrgan {
    private String id;

    private String name;

    private String fatherId;

    public LyUserOrgan(String id, String name, String fatherId) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
    }

    public LyUserOrgan() {
        super();
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }
}