# spring-boot-mvc-initialize

### Just a bit information about project
How to implement MVC for a spring boot project which is answer => it's here

### Code
1. Design BaseDTO
```java
@Setter
@Getter
public abstract class BaseDTO<ID> {

	private ID id;
}
```
2. Design BaseEntity
```java
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity<ID extends Number> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}
```
3. Desgin BaseMapper (using Mapstruct if you use DTO)
```java
public interface BaseMapper<E, D> {

    D toDTO(E e);

    List<D> toDTOs(List<E> eList);

    E toEntity(D d);

    List<E> toEntities(List<D> dList);
}
```
4. Desgin BaseService
```java
public interface IBaseService<D, ID> {

    HttpResponseNew<List<D>> create(List<D> DTO);
    HttpResponseNew<D> update(D DTO);
    HttpResponseNew<List<D>> getAll(Pageable pageable);
    HttpResponseNew<D> getById(ID id);
    HttpResponseNew<String> deleteById(ID id);
}
```
5. Build a BaseServiceImpl
```java
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
```
### Diagram
Will be soon
