package com.k3rcaft.mvcinitialize.service;


import com.k3rcaft.mvcinitialize.model.dto.CategoryDTO;
import com.k3rcaft.mvcinitialize.model.entity.CategoryEntity;
import com.k3rcaft.mvcinitialize.model.mapper.BaseMapper;
import com.k3rcaft.mvcinitialize.service.serviceImpl.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ICategoryService extends BaseServiceImpl<CategoryEntity, CategoryDTO, Long> {

    public ICategoryService(JpaRepository<CategoryEntity, Long> repo, BaseMapper<CategoryEntity, CategoryDTO> mapper) {
        super(repo, mapper);
    }
}
