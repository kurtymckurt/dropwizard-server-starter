
package org.kurtymckurt.dropwizard.server.starter;

import javax.inject.Singleton;

import dagger.Component;
import io.dropwizard.hibernate.HibernateBundle;
import org.kurtymckurt.dropwizard.server.starter.resources.HealthCheckResource;
import org.kurtymckurt.dropwizard.server.starter.resources.PersonResource;
import org.modelmapper.ModelMapper;

@Singleton
@Component(modules = {ServerModule.class})
interface ServerComponent {
  PersonResource getPersonResource();
  HealthCheckResource getHealthCheckResource();
  ModelMapper getModelMapper();
  HibernateBundle<ServerConfiguration> getHibernateBundle();
}
