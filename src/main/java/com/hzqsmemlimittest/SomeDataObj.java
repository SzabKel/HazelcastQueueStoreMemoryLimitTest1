package com.hzqsmemlimittest;

import java.io.Serializable;
import java.time.Instant;

public class SomeDataObj implements Serializable {
    private long id;
    private String text;
    private Instant createdAt = Instant.now();

    public SomeDataObj() {
    }

    public SomeDataObj(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
