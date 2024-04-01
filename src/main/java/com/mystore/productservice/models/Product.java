package com.mystore.productservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Product implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String name;
    private double price;
    private String description;
    private String color;

    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="category_code",referencedColumnName = "code",nullable = false)
    private Category category;
    private double averageRating;


}
