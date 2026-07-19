package com.bang.authservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Categories",
indexes = {
        @Index(name="idx_category_parent", columnList = "parent_id"),
        @Index(name = "idx_category_path", columnList = "path"),
        @Index(name="idx_category_slug", columnList = "slug")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(200)")
    private String name;

    @Column(unique = true, length = 200)
    private String slug;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL
    )
    private List<Category> chidren = new ArrayList<>();

    private Integer level;

    @Column(length = 500)
    private String path;

    private Integer sortOrder;

    private Integer productCount;


    private Boolean isActive;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if(level == null)
            level = 0;
        if(productCount == null)
            productCount = 0;

        if(isActive == null)
            isActive = true;
    }
    @PreUpdate
    public void preUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
