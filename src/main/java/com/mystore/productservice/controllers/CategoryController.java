package com.mystore.productservice.controllers;

import com.mystore.productservice.dtos.CategoryDto;
import com.mystore.productservice.dtos.ResponseDto;
import com.mystore.productservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        categoryDto = categoryService.createCategory(categoryDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{code}")
                .buildAndExpand(categoryDto.getCode())
                .toUri();

        return ResponseEntity.created(location).build();

    }
    @GetMapping
    public ResponseEntity<ResponseDto<Map<String,Object>> >getAllCategories(@RequestParam(defaultValue = "1") Integer page ){
        return ResponseEntity.ok(new ResponseDto<>("Successful","00",categoryService.getAllCategories(page)));
    }

    @GetMapping("/{code}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String code){
        return ResponseEntity.ok(categoryService.getCategory(code));
    }
}
