package com.k3rcaft.mvcinitialize.model.mapper;

import com.k3rcaft.mvcinitialize.model.dto.CategoryDTO;
import com.k3rcaft.mvcinitialize.model.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryEntity, CategoryDTO> {
}
