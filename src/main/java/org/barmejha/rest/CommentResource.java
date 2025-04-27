package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.barmejha.domain.dtos.CommentDTO;
import org.barmejha.domain.entities.communities.Comment;
import org.barmejha.rest.interfaces.AbstractEntityResource;
import org.barmejha.services.CommentService;

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CommentResource extends AbstractEntityResource<Comment, CommentDTO> {
  public CommentResource(CommentService inheritedEntityService) {
    super(inheritedEntityService);
  }
}