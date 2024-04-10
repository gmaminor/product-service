package com.mystore.productservice.dtos;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ResponseDto<T> implements Serializable {
    private String status;
    private String code;
    private T body;

}
