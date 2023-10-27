package com.tinyurl_system_design.key_generator_service.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Table
public class GeneratedKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    @Column(columnDefinition = "serial", updatable = false)
    private Long id;

    private String key;

    private LocalDateTime dateCreated;

    private boolean consumed;

    public GeneratedKey(Long id, String key, LocalDateTime dateCreated, boolean consumed) {
        this.id = id;
        this.key = key;
        this.dateCreated = dateCreated;
        this.consumed = consumed;
    }


    public GeneratedKey(String key, LocalDateTime dateCreated, boolean consumed) {
        this.key = key;
        this.dateCreated = dateCreated;
        this.consumed = consumed;
    }

    public GeneratedKey() {}

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

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    @Override
    public String toString() {
        return "GeneratedKey{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", dateCreated=" + dateCreated +
                ", consumed=" + consumed +
                '}';
    }
}
