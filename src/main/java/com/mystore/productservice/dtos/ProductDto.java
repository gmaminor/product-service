package com.mystore.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String name;
    private double price;
    private String description;
    private String color;
    private String imageUrl;
    private String categoryCode;
    private double averageRating;
}
