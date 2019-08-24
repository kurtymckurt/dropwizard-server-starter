
package org.kurtymckurt.dropwizard.server.starter;

import com.codahale.metrics.health.HealthCheck;

public class ServerHealthCheck extends HealthCheck {
  protected Result check() throws Exception {
    return Result.healthy();
  }
}
