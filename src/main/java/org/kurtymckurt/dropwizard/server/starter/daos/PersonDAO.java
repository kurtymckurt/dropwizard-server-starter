package org.kurtymckurt.dropwizard.server.starter.daos;

import javax.inject.Singleton;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.kurtymckurt.dropwizard.server.starter.models.Person;

@Singleton
public class PersonDAO extends AbstractDAO<Person> {
  public PersonDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public Person findById(long id) {
    return super.get(id);
  }

  public Person save(Person entity) throws HibernateException {
    return super.persist(entity);
  }

  public void delete(Person entity) {
    super.currentSession().delete(entity);
  }
}
