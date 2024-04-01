package com.mystore.productservice.services;

import com.mystore.productservice.configs.ModelMapperConfig;
import com.mystore.productservice.dtos.CategoryDto;
import com.mystore.productservice.models.Category;
import com.mystore.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final String CLIENT = "ORANTOOR";//Todo Create a model for clients
    private static final int CLIENT_PORTION = 4;
    public CategoryDto createCategory(CategoryDto categoryDto){
        String clientTag = CLIENT.length() < CLIENT_PORTION ? CLIENT : CLIENT.substring(0,4);
        categoryDto.setCode(categoryDto.getName().replace(" ","").concat(clientTag));

       Category category= categoryRepository.save(ModelMapperConfig.modelMapper()
               .map(categoryDto, Category.class));
       return ModelMapperConfig.modelMapper().map(category,CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream().map(category -> ModelMapperConfig.modelMapper()
                        .map(category, CategoryDto.class)).toList();
    }

}
