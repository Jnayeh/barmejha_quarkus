package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.domain.entities.communities.Comment;
import org.barmejha.repositories.CommentRepository;
import org.barmejha.services.interfaces.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class CommentService implements IEntityService<Comment> {

  private final CommentRepository commentRepository;

  @Override
  @WithSession
  public Uni<List<Comment>> getAll(HttpHeaders headers, String lang, String tenantId) {
    return commentRepository.listAll();
  }

  @Override
  @WithSession
  public Uni<List<Comment>> query(HttpHeaders headers, QueryRequest<Comment> queryRequest) {
    return commentRepository.findByQuery(queryRequest);
  }

  @Override
  @WithSession
  public Uni<Comment> getById(HttpHeaders headers, Long id) {
    return commentRepository.findById(id);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> create(HttpHeaders headers, Comment entity) {
    return commentRepository.persist(entity).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> update(HttpHeaders headers, Long id, Comment updatedEntity) {
    return commentRepository.findById(id).onItem().transform(found -> {
      found.setContent(updatedEntity.getContent());
      return found;
    }).map(commentRepository::persist).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  @Transactional
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return commentRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest<Comment> request) {
    return commentRepository.countByQuery(request);
  }
}