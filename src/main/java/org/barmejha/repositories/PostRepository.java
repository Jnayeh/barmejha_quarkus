package org.barmejha.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.communities.Post;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class PostRepository extends GenericRepository<Post> {
  @Override
  protected String getEntityName() {
    return "Post e";
  }
}