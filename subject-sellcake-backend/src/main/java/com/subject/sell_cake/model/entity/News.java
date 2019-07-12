package com.subject.sell_cake.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "news")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class News{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.subject.sell_cake.util.IDGeneratorUtil")
    @Column(name = "new_id", nullable = false, length = 255)
    private String newId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "images")
    private String images;
    @Column(name = "status")
    private int status;
    @CreatedDate
    @Column(name = "create_at",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @LastModifiedDate
    @Column(name = "update_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivity;

    public String getNewId() {
        return newId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImages() {
        return images;
    }

    public int getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }
}
