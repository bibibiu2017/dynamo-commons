package ke.co.dynamodigital.commons.config.data;

import com.github.javafaker.Faker;

import java.util.function.*;

/**
 * @author arthurmita
 * created 27/06/2021 at 16:45
 **/
@SuppressWarnings("unchecked")
public abstract class Mother<C, M extends Mother<C, M>> {
    protected final static Faker faker = Faker.instance();
    protected final C child;

    protected Mother(C child) {
        this.child = child;
    }

    protected Mother(Supplier<C> mother) {
        this(mother.get());
    }

    public <T> M with(BiConsumer<C, T> setter, T value) {
        setter.accept(this.child, value);
        return (M) this;
    }

    public C child() {
        return this.child;
    }
}
