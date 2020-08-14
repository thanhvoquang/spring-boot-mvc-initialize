package com.k3rcaft.mvcinitialize.model.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO extends BaseDTO<Long> {

    private String name;
    private String description;
    private String urlImage;
}
