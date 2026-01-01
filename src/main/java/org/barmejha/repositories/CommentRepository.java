package org.barmejha.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.communities.Comment;
import org.barmejha.repositories.generic.GenericRepository;

@ApplicationScoped
public class CommentRepository extends GenericRepository<Comment> {
  @Override
  protected String getEntityName() {
    return "Comment e";
  }
}