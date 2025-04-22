package org.barmejha.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.barmejha.domain.dtos.PostDTO;
import org.barmejha.domain.entities.communities.Post;
import org.barmejha.rest.interfaces.AbstractEntityResource;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@ApplicationScoped
public class PostResource extends AbstractEntityResource<Post, PostDTO> {
}