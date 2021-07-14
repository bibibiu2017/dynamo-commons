package ke.co.dynamodigital.commons.config.mother;

import ke.co.dynamodigital.commons.models.base.BaseModel;

import java.time.LocalDateTime;

/**
 * @author arthurmita
 * created 27/06/2021 at 16:56
 **/
public abstract class PersistableModelMother<C extends BaseModel> extends ModelMother<C> {

    protected PersistableModelMother(C child) {
        super(child);
    }

    public PersistableModelMother<C> merged() {
        return this.with(C::setCreatedOn, LocalDateTime.now())
                .with(C::setUpdatedOn, LocalDateTime.now())
                .with(C::setId, FAKER.number().numberBetween(1, 1000L))
                .with(C::setVersion, FAKER.number().numberBetween(1, 100L));
    }

    public PersistableModelMother<C> detached() {
        return this.with(C::setCreatedOn, null)
                .with(C::setUpdatedOn, null)
                .with(C::setId, null)
                .with(C::setVersion, null);
    }
}