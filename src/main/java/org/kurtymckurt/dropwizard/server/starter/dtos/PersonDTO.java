package org.kurtymckurt.dropwizard.server.starter.dtos;

import lombok.Data;

@Data
public class PersonDTO {
  private long id;
  private String firstName;
  private String lastName;
  private int age;
}
