package org.barmejha.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.repositories.MyEntityRepository;

import java.util.HashMap;

@Path("/hello")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

  @Inject
  MyEntityRepository myEntityRepository;

  @GET
  public HashMap<String, String> hello() {
    var h = new HashMap<String, String>();
    h.put("hello", "Hello from Quarkus REST");
    return h;
  }
}
