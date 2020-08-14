package com.k3rcaft.mvcinitialize.repository;

import com.k3rcaft.mvcinitialize.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
