package org.barmejha.rest;

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
import lombok.AllArgsConstructor;
import org.barmejha.domain.entities.Category;
import org.barmejha.repositories.CategoryRepository;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class CategoryResource {

  private final CategoryRepository categoryRepository;

  @GET
  public List<Category> getAllCategories() {
    return categoryRepository.listAll();
  }

  @GET
  @Path("/{id}")
  public Category getCategoryById(@PathParam("id") Long id) {
    return categoryRepository.findById(id);
  }

  @POST
  @Transactional
  public Response createCategory(Category category) {
    categoryRepository.persist(category);
    return Response.status(201).entity(category).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Category updateCategory(@PathParam("id") Long id, Category updatedCategory) {
    Category category = categoryRepository.findById(id);
    if (category != null) {
      category.setName(updatedCategory.getName());
      category.setHexColor(updatedCategory.getHexColor());
    }
    return category;
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteCategory(@PathParam("id") Long id) {
    categoryRepository.deleteById(id);
  }
}