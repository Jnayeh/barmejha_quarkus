package org.barmejha.domain.mappers;

import org.barmejha.domain.dtos.PaymentDTO;
import org.barmejha.domain.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    config = MappingConfig.class,
    uses = {
        PlanMapper.class,
        UserMapper.class
    }
)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    // Entity → DTO
    PaymentDTO toDTO(Payment entity);

    // DTO → Entity
    Payment toEntity(PaymentDTO dto);
}