
package org.kurtymckurt.dropwizard.server.starter;

import javax.inject.Singleton;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;

@Singleton
public class ServerHibernationBundle extends ScanningHibernateBundle<ServerConfiguration> {

  public ServerHibernationBundle(String pckg) {
    super(pckg);
  }

  @Override
    public PooledDataSourceFactory getDataSourceFactory(ServerConfiguration serverConfiguration) {
      return serverConfiguration.getDatabase();
    }
}
