package com.mystore.productservice.services;

import com.mystore.productservice.configs.ModelMapperConfig;
import com.mystore.productservice.dtos.ProductDto;
import com.mystore.productservice.dtos.ResponseDto;
import com.mystore.productservice.models.Category;
import com.mystore.productservice.models.Product;
import com.mystore.productservice.repositories.CategoryRepository;
import com.mystore.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperConfig modelMapper;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<ResponseDto<ProductDto>> addProduct(ProductDto productDto) {
        Product product = ModelMapperConfig.modelMapper().map(productDto, Product.class);
        Category category = categoryRepository.findByCode(productDto.getCategoryCode())
                .orElseThrow(()-> new RuntimeException("Category Not Found"));//Todo use custom exception
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        productDto = ModelMapperConfig.modelMapper().map(savedProduct, ProductDto.class);
        return ResponseEntity.ok(new ResponseDto<>("Successful","00",productDto));
//        change to create 201
    }
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ModelMapperConfig
                .modelMapper().map(product,ProductDto.class)).toList();
    }
}
