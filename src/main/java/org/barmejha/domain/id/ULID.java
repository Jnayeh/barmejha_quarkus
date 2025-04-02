package org.barmejha.domain.id;

import org.barmejha.domain.id.generators.ULIDGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IdGeneratorType(ULIDGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ULID {
}
