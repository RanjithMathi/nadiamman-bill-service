package com.billflow.mapper;

import com.billflow.dto.QuoteDTO;
import com.billflow.model.Quote;

public class QuoteMapper {

    // Convert DTO → Entity
    public static Quote toEntity(QuoteDTO dto) {
        if (dto == null) {
            return null;
        }

        Quote quote = new Quote();
        quote.setId(dto.getId());
        quote.setClientId(dto.getClientId());
        quote.setStatus(dto.getStatus());
        quote.setDescription(dto.getDescription());
        quote.setTotalAmount(dto.getTotalAmount());
        quote.setCreatedAt(dto.getCreatedAt());
        quote.setUpdatedAt(dto.getUpdatedAt());

        return quote;
    }

    // Convert Entity → DTO
    public static QuoteDTO toDTO(Quote entity) {
        if (entity == null) {
            return null;
        }

        QuoteDTO dto = new QuoteDTO();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClientId());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
