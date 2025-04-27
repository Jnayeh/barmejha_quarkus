package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.CategoryDTO;
import org.barmejha.domain.entities.Category;
import org.barmejha.rest.interfaces.AbstractEntityResource;
import org.barmejha.services.CategoryService;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CategoryResource extends AbstractEntityResource<Category, CategoryDTO> {
  public CategoryResource(CategoryService inheritedEntityService) {
    super(inheritedEntityService);
  }
}