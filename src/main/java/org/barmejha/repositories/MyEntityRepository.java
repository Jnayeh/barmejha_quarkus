package org.barmejha.repositories;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import jakarta.enterprise.context.ApplicationScoped;
import org.barmejha.domain.entities.MyEntity;

@ApplicationScoped
public interface MyEntityRepository extends PanacheEntityResource<MyEntity, Long> {
}