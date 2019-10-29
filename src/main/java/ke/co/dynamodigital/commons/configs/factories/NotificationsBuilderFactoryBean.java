package ke.co.dynamodigital.commons.configs.factories;

import ke.co.dynamodigital.commons.services.NotificationsBuilder;
import ke.co.dynamodigital.commons.services.NotificationsBuilderImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Bibibiu
 * created 10/20/19 at 07:23
 **/
public class NotificationsBuilderFactoryBean implements FactoryBean<NotificationsBuilder> {
    @Override
    public NotificationsBuilder getObject() throws Exception {
        return new NotificationsBuilderImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return NotificationsBuilderImpl.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
