
package org.kurtymckurt.dropwizard.server.starter;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<ServerConfiguration> {

  private HibernateBundle<ServerConfiguration> hibernateBundle = new ServerHibernationBundle(
          "org.kurtymckurt.dropwizard.server.starter");

  public static void main(String[] args) throws Exception {
    new ServerApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                    new EnvironmentVariableSubstitutor(false)
            )
    );

    bootstrap.addBundle(new MigrationsBundle<ServerConfiguration>() {
      @Override
      public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
        return configuration.getDatabase();
      }
    });
    bootstrap.addBundle(hibernateBundle);
    super.initialize(bootstrap);
  }

  public void run(ServerConfiguration serverConfiguration, Environment environment) throws Exception {
    ServerComponent serverComponent = DaggerServerComponent.builder().serverModule(
            new ServerModule(serverConfiguration, hibernateBundle)
    ).build();
    environment.healthChecks().register("server-health-check", new ServerHealthCheck());
    addResources(serverComponent, environment);
  }

  private void addResources(ServerComponent serverComponent, Environment environment) {
    environment.jersey().register(serverComponent.getHealthCheckResource());
    environment.jersey().register(serverComponent.getPersonResource());
  }
}
