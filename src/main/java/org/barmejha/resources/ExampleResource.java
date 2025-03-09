package org.barmejha.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("/hello")
@ApplicationScoped
public class ExampleResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from Quarkus REST";
  }

  @GET
  @Path("/json")
  @Produces(MediaType.APPLICATION_JSON)

  public Map<String, String> helloJson() {
    var h = new HashMap<String, String>();
    h.put("hello", "Hello from Quarkus REST");
    return h;
  }
}
