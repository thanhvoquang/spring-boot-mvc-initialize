package com.k3rcaft.mvcinitialize.service.serviceImpl;

import com.k3rcaft.mvcinitialize.model.dto.BaseDTO;
import com.k3rcaft.mvcinitialize.model.entity.BaseEntity;
import com.k3rcaft.mvcinitialize.model.mapper.BaseMapper;
import com.k3rcaft.mvcinitialize.model.response.HttpResponseNew;
import com.k3rcaft.mvcinitialize.model.response.RestResponsePage;
import com.k3rcaft.mvcinitialize.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Base of ServiceCoreImpl to handle commom http interactive
 *
 * @param <E> Entity
 * @param <D> DTO
 * @param <ID> ID Type
 */
public abstract class BaseServiceImpl<E extends BaseEntity<ID>, D extends BaseDTO<ID>, ID extends Long> implements IBaseService<D, ID> {

    JpaRepository<E, ID> repo;
    BaseMapper<E, D> mapper;

    public BaseServiceImpl(JpaRepository<E, ID> repo, BaseMapper<E, D> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public HttpResponseNew<List<D>> create(List<D> dList) {
        List<E> eList = mapper.toEntities(dList);
        repo.saveAll(eList);
        return HttpResponseNew.successful("Create entities successful");
    }

    @Override
    public HttpResponseNew<D> update(D d) {
        E e = mapper.toEntity(d);
        Optional<E> eOptional = repo.findById(e.getId());
        if (eOptional.isEmpty()) {
            return HttpResponseNew.failure("Not find object with this id to update", 200);
        }
        E eSave = eOptional.get();
        repo.saveAndFlush(eSave);
        return HttpResponseNew.successful("Update entities successful", d);
    }

    @Override
    public HttpResponseNew<List<D>> getAll(Pageable pageable) {
        Page<E> ePage = repo.findAll(pageable);
        Page<D> dPage = ePage.map(mapper::toDTO);
        RestResponsePage<D> dRestResponsePage = new RestResponsePage<>(dPage.getContent(), dPage.getPageable(), dPage.getTotalElements());
        return HttpResponseNew.successful("Get all is success" ,dRestResponsePage);
    }

    @Override
    public HttpResponseNew<D> getById(ID id) {
        Optional<E> eOptional = repo.findById(id);
        if (eOptional.isEmpty()) {
            return HttpResponseNew.failure("Not find object with this id", 500);
        }
        D d = mapper.toDTO(eOptional.get());
        return HttpResponseNew.successful("Find it successful", d);
    }

    @Override
    public HttpResponseNew<String> deleteById(ID id) {
        Optional<E> eOptional = repo.findById(id);
        if(eOptional.isEmpty()) {
            return HttpResponseNew.failure("Not find object with this id", 500);
        }
        repo.deleteById(id);
        return HttpResponseNew.successful(String.format("Delete object successful with id %s", id));
    }
}
