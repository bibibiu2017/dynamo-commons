package ke.co.dynamodigital.commons.services;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * @author Bibibiu
 * created 8/28/19 at 08:08
 **/
public interface GenericCrudService<T, ID extends Serializable> {
    /**
     * Finds all persisted entities of type T
     *
     * @return List of entities of type T
     */
    List<T> findAll();

    /**
     * Finds entity of Type T using entity id
     *
     * @param id Entity id
     * @return Found entity or throws entity not found exception
     * @throws EntityNotFoundException when entity with given id is not found
     */
    T findById(ID id) throws EntityNotFoundException;

    /**
     * Persists entity of type {@code T}
     *
     * @param var entity to save
     * @return saved entity {@code T}
     */
    T save(T var);

    /**
     * Deletes entity using entities id
     *
     * @param id Entity id used to delete it
     */
    void deleteById(ID id);

    /**
     * Delete persisted entity
     *
     * @param var Persisted entity to be deleted
     */
    void delete(T var);


}
