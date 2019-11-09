package ke.co.dynamodigital.commons.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bibibiu
 * created 8/28/19 at 08:11
 **/
@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class GenericCrudServiceImpl<T, ID extends Serializable> implements GenericCrudService<T, ID> {
    @Autowired
    protected JpaRepository<T, ID> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T findById(ID id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with ID-->" + id + "<--not found"));
    }

    @Override
    public T save(T var) {
        return repository.save(var);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T var) {
        repository.delete(var);
    }

}

