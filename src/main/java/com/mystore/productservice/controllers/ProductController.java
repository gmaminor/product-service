package com.mystore.productservice.controllers;

import com.mystore.productservice.dtos.ProductDto;
import com.mystore.productservice.dtos.ResponseDto;
import com.mystore.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ProductDto>> addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts(){
        return ResponseEntity.ok(new ResponseDto<>("Successful","00",productService.getAllProducts()));
    }
}
