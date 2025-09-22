package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.TicketRequest;
import com.quynhtien.cineasy.dto.response.TicketResponse;
import com.quynhtien.cineasy.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {
    Ticket toTicket(TicketRequest request);

    TicketResponse toTicketResponse(Ticket ticket);

    void updateTicket(TicketRequest request, @MappingTarget Ticket ticket);
}

