package org.kurtymckurt.dropwizard.server.starter;

import java.io.IOException;
import java.util.Properties;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.dropwizard.hibernate.HibernateBundle;
import lombok.extern.slf4j.Slf4j;
import org.kurtymckurt.dropwizard.server.starter.daos.PersonDAO;
import org.kurtymckurt.dropwizard.server.starter.models.Version;
import org.modelmapper.ModelMapper;

@Module
@Slf4j
public class ServerModule {

  private final HibernateBundle<ServerConfiguration> hibernateBundle;
  private final ServerConfiguration serverConfiguration;

  public ServerModule(ServerConfiguration serverConfiguration, HibernateBundle<ServerConfiguration> hibernateBundle) {
    this.serverConfiguration = serverConfiguration;
    this.hibernateBundle = hibernateBundle;
  }

  @Provides
  @Singleton
  PersonDAO personDAO (){
    return new PersonDAO(hibernateBundle.getSessionFactory());
  }

  @Provides
  @Singleton
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Provides
  @Singleton
  HibernateBundle<ServerConfiguration> hibernateBundle() {
    return hibernateBundle;
  }

  @Provides
  @Singleton
  Version version() {
    final Properties properties = new Properties();
    String version = "unknown";
    try {
      properties.load(ServerModule.class.getClassLoader().getResourceAsStream("project.properties"));
      version = properties.getProperty("version");
    } catch (IOException e) {
      log.warn("Couldn't read project.properties file", e);
    }
    return new Version(version);
  }
}
