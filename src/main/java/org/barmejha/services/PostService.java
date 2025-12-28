package org.barmejha.services;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.barmejha.config.utils.HeaderHolder;
import org.barmejha.domain.dtos.PostDTO;
import org.barmejha.domain.entities.communities.Post;
import org.barmejha.domain.request.QueryRequest;
import org.barmejha.repositories.PostRepository;
import org.barmejha.services._interface.IEntityService;
import org.barmejha.services.utils.ServiceUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@RequiredArgsConstructor
public class PostService implements IEntityService<Post, PostDTO> {

  private final PostRepository postRepository;
  private final HeaderHolder headerHolder;

  @Override
  @WithSession
  public Uni<List<PostDTO>> getAll(HttpHeaders headers) {
    return query(headers, QueryRequest.builder().build());
  }

  @Override
  @WithSession
  public Uni<List<PostDTO>> query(HttpHeaders headers, QueryRequest queryRequest) {
    queryRequest.setJoins(new ArrayList<>(initJoins(queryRequest)));
    return postRepository.findByQuery(queryRequest).map(entityList -> toDTO(entityList, queryRequest.getJoins()));
  }

  @Override
  @WithSession
  public Uni<PostDTO> getById(HttpHeaders headers, Long id) {
    return postRepository.findById(id).map(this::toDTO);
  }

  @Override
  @WithTransaction
  public Uni<Response> create(HttpHeaders headers, Post entity) {
    return postRepository.persist(entity).map(this::toDTO).map(ServiceUtils::createdResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> update(HttpHeaders headers, Long id, Post updatedEntity) {
    return postRepository.findById(id).onItem().transform(found -> {
      found.setContent(updatedEntity.getContent());
      found.setTitle(updatedEntity.getTitle());
      return found;
    }).flatMap(postRepository::persist).map(this::toDTO).map(ServiceUtils::okResponse);
  }

  @Override
  @WithTransaction
  public Uni<Response> delete(HttpHeaders headers, Long id) {
    return postRepository.deleteById(id).map(isDeleted -> {
      if (Boolean.TRUE.equals(isDeleted)) return Response.status(204).build();
      return Response.status(404).build();
    });
  }

  @Override
  @WithSession
  public Uni<Long> count(HttpHeaders headers, QueryRequest request) {
    return postRepository.countByQuery(request);
  }

  @Override
  public PostDTO toDTO(Post entity) {
      return toDTO(entity, List.of());
  }

  @Override
  public PostDTO toDTO(Post entity, List<String> joins) {
      if (entity == null) return null;
      return PostDTO.fromEntity(entity, joins, headerHolder.getLang());
  }

  public Set<String> initJoins(QueryRequest queryRequest) {
    HashSet<String> joins = new HashSet<>(Set.of("community", "author"));
    if (queryRequest.getJoins() == null) {
      return joins;
    }
    joins.addAll(queryRequest.getJoins());
    return joins;
  }
}