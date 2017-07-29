package com.bins.stringformatperformance.chapterone;

import java.io.Serializable;

/**
 * Created by songrongbin on 2017/7/29.
 */
public class Param implements Serializable {

    private static final long serialVersionUID = -6895107657018875530L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
