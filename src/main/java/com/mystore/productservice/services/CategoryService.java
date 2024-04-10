package com.mystore.productservice.services;

import com.mystore.productservice.configs.ModelMapperConfig;
import com.mystore.productservice.dtos.CategoryDto;
import com.mystore.productservice.models.Category;
import com.mystore.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final String CLIENT = "ORANTOOR";//Todo Create a model for clients
    private static final int CLIENT_PORTION = 4;
    private static final int PAGE_SIZE = 5;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        String clientTag = CLIENT.substring(0, 4);
        categoryDto.setCode(categoryDto.getName().replace(" ", "").concat(clientTag));

        Category category = categoryRepository.save(ModelMapperConfig.modelMapper()
                .map(categoryDto, Category.class));
        return ModelMapperConfig.modelMapper().map(category, CategoryDto.class);
    }

    public Map<String,Object> getAllCategories(Integer page) {
        Pageable pageable = PageRequest.of(page-1, PAGE_SIZE);
        int[] count = {page*PAGE_SIZE-5};//get starting number of the page
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryDto> categoryDtos = categories.getContent().stream().map(category -> {
                    category.setId(++count[0]);
                    return ModelMapperConfig.modelMapper().map(category, CategoryDto.class);
                }
        ).toList();

        Map<String,Object> response = new HashMap<>();
        response.put("page",page);
        response.put("pageSize",PAGE_SIZE);
        response.put("content",categoryDtos);
        response.put("totalPages",categories.getTotalPages());
        response.put("totalElements",categories.getTotalElements());
        return response;
    }

    public CategoryDto getCategory(String code) {
        return ModelMapperConfig.modelMapper()
                .map(categoryRepository.findByCode(code).orElse(new Category()), CategoryDto.class);
    }

}
