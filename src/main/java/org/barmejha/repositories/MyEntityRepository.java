package org.barmejha.repositories;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import org.barmejha.domain.entities.MyEntity;

public interface MyEntityRepository extends PanacheEntityResource<MyEntity, Long> {
}