package com.k3rcaft.mvcinitialize.controller;

import com.k3rcaft.mvcinitialize.config.ApiPageable;
import com.k3rcaft.mvcinitialize.model.dto.CategoryDTO;
import com.k3rcaft.mvcinitialize.model.response.HttpResponseNew;
import com.k3rcaft.mvcinitialize.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@Api(tags = {"category-apis"})
public class CategoryController {

    private final ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @ApiPageable
    @ApiOperation(value = "View a list of available products")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseNew<List<CategoryDTO>> findAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @ApiOperation(value = "Find a product")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/show/{id}")
    public HttpResponseNew<CategoryDTO> findById(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation(value = "Add new a product")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseNew<CategoryDTO> add(@RequestBody CategoryDTO dto) {
        return null;
    }

    @ApiOperation(value = "Update a product exist")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseNew<CategoryDTO> update(@RequestBody CategoryDTO dto) {
        service.update(dto);
        return null;
    }

    @ApiOperation(value = "Delete a product exist")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseNew<CategoryDTO> deleteById(@PathVariable("id") Long id) {
        return null;
    }
}
