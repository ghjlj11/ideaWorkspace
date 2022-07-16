package com.ghj.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ghj
 * @since 2022-07-17
 */
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    private String id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客作者
     */
    private String author;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 浏览量
     */
    private Integer views;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Blog{" +
            "id = " + id +
            ", title = " + title +
            ", author = " + author +
            ", createTime = " + createTime +
            ", views = " + views +
        "}";
    }
}
