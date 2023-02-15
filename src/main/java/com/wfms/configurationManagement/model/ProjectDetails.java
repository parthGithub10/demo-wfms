package com.wfms.configurationManagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProjectDetails")
public class ProjectDetails {

    @Id
    private long projectId;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "in_use", nullable = true)
    private boolean inUse;

    @Column(name = "Created_By")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "Updated_By")
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}