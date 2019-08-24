
package org.kurtymckurt.dropwizard.server.starter.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.kurtymckurt.dropwizard.server.starter.dtos.PersonDTO;
import org.kurtymckurt.dropwizard.server.starter.daos.PersonDAO;
import org.kurtymckurt.dropwizard.server.starter.models.Person;
import org.modelmapper.ModelMapper;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

  private PersonDAO personDAO;
  private ModelMapper modelMapper;

  @Inject
  public PersonResource (PersonDAO personDAO, ModelMapper modelMapper) {
    this.personDAO = personDAO;
    this.modelMapper = modelMapper;
  }

  @Path("/{id}")
  @GET
  @UnitOfWork
  public Response getPerson(@PathParam("id") LongParam id) {
    Person person = personDAO.findById(id.get());
    if(person == null) {
      return Response.status(404).build();
    }
    return Response.ok(modelMapper.map(person, PersonDTO.class)).build();
  }

  @POST
  @UnitOfWork
  public Response savePerson(PersonDTO personDTO) {
    Person person = modelMapper.map(personDTO, Person.class);
    person = personDAO.save(person);
    return Response.ok(modelMapper.map(person, PersonDTO.class)).build();
  }

}
