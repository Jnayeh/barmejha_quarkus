package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.CommentDTO;
import org.barmejha.domain.entities.communities.Comment;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.CommentRepository;
import org.barmejha.services._interface.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@RequiredArgsConstructor
public class CommentService implements IEntityService<Comment, CommentDTO> {

  private final CommentRepository commentRepository;
  private final HeaderHolder headerHolder;

  @Override
  @WithSession
  public Uni<List<CommentDTO>> getAll(HttpHeaders headers) {
    return query(headers, QueryRequest.builder().build());
  }

  @Override
  @WithSession
  public Uni<List<CommentDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    queryRequest.setJoins(new ArrayList<>(initJoins(queryRequest)));
    return commentRepository.findByQuery(queryRequest).map(entityList -> toDTO(entityList, queryRequest.getJoins()));
  }

  @Override
  @WithSession
  public Uni<CommentDTO> getById(HttpHeaders headers, Long id) {
    return commentRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, Comment entity) {
    return commentRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, Comment updatedEntity) {
    return commentRepository.findById(id).onItem().transform(found -> {
      found.setContent(updatedEntity.getContent());
      return found;
    }).flatMap(commentRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return commentRepository.deleteById(id).map(isDeleted -> {
      if (isDeleted) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return commentRepository.countByQuery(request);
  }

  @Override
  public CommentDTO toDTO(Comment entity) {
      return toDTO(entity, List.of());
  }

  @Override
  public CommentDTO toDTO(Comment entity, List<String> joins) {
      if (entity == null) return null;
      return CommentDTO.fromEntity(entity, joins, headerHolder.getLang());
  }

    public Set<String> initJoins(QueryRequest queryRequest) {
    HashSet<String> joins = new HashSet<>(Set.of("author"));
    if (queryRequest.getJoins() == null) {
      return joins;
    }
    joins.addAll(queryRequest.getJoins());
    return joins;
  }
}