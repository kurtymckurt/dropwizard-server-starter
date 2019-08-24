package org.kurtymckurt.dropwizard.server.starter.models;

import javax.inject.Singleton;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Singleton
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Version {
  private String version;
}
