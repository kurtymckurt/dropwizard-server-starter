
package org.kurtymckurt.dropwizard.server.starter.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kurtymckurt.dropwizard.server.starter.dtos.VersionDTO;
import org.kurtymckurt.dropwizard.server.starter.models.Version;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthCheckResource {

  private Version version;

  @Inject
  public HealthCheckResource(Version version) {
    this.version = version;
  }

  @GET
  public VersionDTO healthCheck() {
    return VersionDTO.builder().version(version.getVersion()).build();
  }
}
