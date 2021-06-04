package ke.co.dynamodigital.commons.models.base;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arthurmita
 * created 04/06/2021 at 22:48
 **/
@SuppressWarnings("ALL")
public abstract class BasePersistenceAdapter<M extends BaseModel,E extends BaseEntity, R extends JpaRepository<E, Long>> {

    protected final Class<M> modelType;
    protected final Class<E> entityType;
    protected ModelMapper mapper;
    protected R repository;

    protected BasePersistenceAdapter() {
        var typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), BasePersistenceAdapter.class);
        assert typeArguments != null;
        this.modelType = (Class<M>) typeArguments[0];
        this.entityType = (Class<E>) typeArguments[1];
    }

    @Autowired
    protected void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    protected void setRepository(R r) {
        this.repository = r;
    }

    protected M mapToModel(E toMap) {
        return mapper.map(toMap, modelType);
    }

    protected E mapToEntity(M toMap) {
        return mapper.map(toMap, entityType);
    }

    protected E save(E toSave) {
        return repository.save(toSave);
    }

    protected boolean exists(M toCheck) {
        return repository.existsById(toCheck.getId());
    }

    protected boolean notExists(M toCheck) {
        return !exists(toCheck);
    }
}
