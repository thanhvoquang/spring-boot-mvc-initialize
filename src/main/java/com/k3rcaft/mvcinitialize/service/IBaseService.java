package com.k3rcaft.mvcinitialize.service;

import com.k3rcaft.mvcinitialize.model.response.HttpResponseNew;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Is base service for child service other
 * Providing some necessary functions
 *
 * @param <D> DTO data for endpoints
 * @param <ID> DTO data's id type
 */
public interface IBaseService<D, ID> {

    HttpResponseNew<List<D>> create(List<D> DTO);
    HttpResponseNew<D> update(D DTO);
    HttpResponseNew<List<D>> getAll(Pageable pageable);
    HttpResponseNew<D> getById(ID id);
    HttpResponseNew<String> deleteById(ID id);
}
