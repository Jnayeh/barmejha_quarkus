package org.barmejha.domain.idgenerator;

import org.barmejha.domain.idgenerator.snowflake.SnowflakeIdGenerator;
import org.eclipse.microprofile.config.ConfigProvider;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class USIDGenerator implements IdentifierGenerator {

  @Override
  public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
    Integer workerID = ConfigProvider.getConfig().getValue("snowflake.worker", Integer.class);
    Integer dataCenterID = ConfigProvider.getConfig().getValue("snowflake.datacenter", Integer.class);
    SnowflakeIdGenerator generator = new SnowflakeIdGenerator(dataCenterID, workerID);
    return generator.nextId();
  }
}