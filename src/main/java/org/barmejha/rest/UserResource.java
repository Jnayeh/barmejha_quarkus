package org.barmejha.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.barmejha.domain.entities.users.User;
import org.barmejha.repositories.UserRepository;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  @Inject
  UserRepository userRepository;

  @GET
  public List<User> getAllUsers() {
    return userRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public User getUserById(@PathParam("id") Long id) {
    return userRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createUser(User user) {
    userRepository.persist(user);
    return Response.status(201).entity(user).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public User updateUser(@PathParam("id") Long id, User updatedUser) {
    User user = userRepository.findById(id);
    if (user != null) {
      user.setEmail(updatedUser.getEmail());
      user.setPasswordHash(updatedUser.getPasswordHash());
      user.setFirstName(user.getFirstName());
      user.setUserName(user.getUserName());
      user.setLastName(user.getLastName());
    }
    return user;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteUser(@PathParam("id") Long id) {
    userRepository.deleteById(id);
  }
}