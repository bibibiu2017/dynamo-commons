package ke.co.dynamodigital.commons.config.mother;

import com.github.javafaker.Faker;

/**
 * @author arthurmita
 * created 19/01/2021 at 11:28
 **/
abstract class BaseMother<C, M> {

    protected static final Faker faker = Faker.instance();
    protected final M mother;

    protected BaseMother(M mother) {
        this.mother = mother;
    }

    public M mother() {
        return this.mother;
    }

    public abstract C child();
}
