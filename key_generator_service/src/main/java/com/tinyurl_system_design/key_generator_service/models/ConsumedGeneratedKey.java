package com.tinyurl_system_design.key_generator_service.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Table
public class ConsumedGeneratedKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    @Column(columnDefinition = "serial", updatable = false)
    private Long id;

    private String key;

    private LocalDateTime dateCreated;

    public ConsumedGeneratedKey(String key, LocalDateTime dateCreated) {
        this.key = key;
        this.dateCreated = dateCreated;
    }

    public ConsumedGeneratedKey() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "ConsumedGeneratedKey{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
