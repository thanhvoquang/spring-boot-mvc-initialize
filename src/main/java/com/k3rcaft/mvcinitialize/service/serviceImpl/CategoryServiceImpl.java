package com.k3rcaft.mvcinitialize.service.serviceImpl;

import com.k3rcaft.mvcinitialize.model.mapper.CategoryMapper;
import com.k3rcaft.mvcinitialize.repository.CategoryRepository;
import com.k3rcaft.mvcinitialize.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ICategoryService {

    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repo, CategoryMapper mapper) {
        super(repo, mapper);
        this.repo = repo;
        this.mapper = mapper;
    }
}
