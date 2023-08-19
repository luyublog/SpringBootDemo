package com.east.demo.persist.entity;

import lombok.Data;

@Data
public class LyUserOrgan {
    private Integer id;

    private String name;

    private Integer fatherId;

    public LyUserOrgan(Integer id, String name, Integer fatherId) {
        this.id = id;
        this.name = name;
        this.fatherId = fatherId;
    }

    public LyUserOrgan() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }
}