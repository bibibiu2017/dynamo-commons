package ke.co.dynamodigital.commons.models.base;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

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

    protected Optional<M> create(M m) {
        return Optional.of(m).filter(this::notExists)
                .map(this::mapToEntity).map(this::save)
                .map(this::mapToModel);
    }

    protected Optional<M> update(M m) {
        return Optional.of(m).filter(this::exists)
                .map(this::mapToEntity).map(this::save)
                .map(this::mapToModel);
    }

    protected M mapToModel(E toMap) {
        return mapper.map(toMap, modelType);
    }

    protected E mapToEntity(M toMap) {
        return mapper.map(toMap, entityType);
    }

    protected E save(E toSave) {
        return repository.saveAndFlush(toSave);
    }

    protected boolean exists(M toCheck) {
        return toCheck.getId() != null && repository.existsById(toCheck.getId());
    }

    @Autowired
    protected void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    protected void setRepository(R r) {
        this.repository = r;
    }

    protected boolean notExists(M toCheck) {
        return !exists(toCheck);
    }
}
