package org.barmejha.domain.idgenerator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IdGeneratorType(USIDGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface USID {
}
