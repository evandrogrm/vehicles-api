package com.example.vehicles.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "marks")
@EntityListeners(AuditingEntityListener.class)
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public Mark setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Mark setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Mark setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Mark setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Mark() {
    }

    public Mark(String id, String name, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
