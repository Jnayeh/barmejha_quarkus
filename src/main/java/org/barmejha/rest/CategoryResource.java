package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.barmejha.domain.dtos.CategoryDTO;
import org.barmejha.domain.entities.Category;
import org.barmejha.rest.interfaces.AbstractEntityResource;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@ApplicationScoped
public class CategoryResource extends AbstractEntityResource<Category, CategoryDTO> {
}