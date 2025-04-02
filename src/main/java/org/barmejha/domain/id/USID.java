package org.barmejha.domain.id;

import org.barmejha.domain.id.generators.USIDGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IdGeneratorType(USIDGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface USID {
}
