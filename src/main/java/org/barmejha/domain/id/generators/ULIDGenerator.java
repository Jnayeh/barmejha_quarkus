package org.barmejha.domain.id.generators;

import org.barmejha.domain.id.generators.ulid.Generator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class ULIDGenerator implements IdentifierGenerator {
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) {
    return Generator.generateID();
  }
}
