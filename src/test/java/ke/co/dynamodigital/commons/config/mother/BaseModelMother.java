package ke.co.dynamodigital.commons.config.mother;

import ke.co.dynamodigital.commons.models.base.BaseModel;
import ke.co.dynamodigital.commons.models.base.BaseModel.BaseModelBuilder;

import java.time.LocalDateTime;

/**
 * @author arthurmita
 * created 17/03/2021 at 01:44
 **/
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class BaseModelMother<C extends BaseModel, M extends BaseModelBuilder> extends BaseMother<C, M> {

    protected BaseModelMother(M mother) {
        super(mother);
    }

    protected void merge() {
        this.mother.id(faker.number().numberBetween(1L, 10000L)).version(faker.number().numberBetween(0L, 100L)).createdOn(LocalDateTime.now()).updatedOn(LocalDateTime.now());
    }

    protected void detach() {
        this.mother.id(null).version(null).createdOn(null).updatedOn(null);
    }

    public <T extends BaseModelMother<C, M>> T merged() {
        this.merge();
        return (T) this;
    }

    public <T extends BaseModelMother<C, M>> T detached() {
        this.detach();
        return (T) this;
    }

    public C child() {
        return (C) this.mother.build();
    }
}
