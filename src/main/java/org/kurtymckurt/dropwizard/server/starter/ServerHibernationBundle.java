
package org.kurtymckurt.dropwizard.server.starter;

import javax.inject.Singleton;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

@Singleton
public class ServerHibernationBundle extends HibernateBundle<ServerConfiguration> {

    protected ServerHibernationBundle(Class<?> entity, Class<?>... entities) {
      super(entity, entities);
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(ServerConfiguration serverConfiguration) {
      return serverConfiguration.getDatabase();
    }
}
