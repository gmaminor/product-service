package com.mystore.productservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @NaturalId
    private String code;
    private String description;

    @OneToMany(mappedBy = "category")
    @Cascade(CascadeType.ALL)
    private List<Product> products = new ArrayList<>() ;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL")//default is datetime(6) in mysql and will make the second have 6 d.p
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL")
    private LocalDateTime updatedAt;

    private String createdBy;

}
