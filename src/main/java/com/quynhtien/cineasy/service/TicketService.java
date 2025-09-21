package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.TicketRequest;
import com.quynhtien.cineasy.dto.response.TicketResponse;
import com.quynhtien.cineasy.entity.Ticket;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.TicketMapper;
import com.quynhtien.cineasy.repository.TicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class TicketService {
    TicketRepository ticketRepository;
    TicketMapper ticketMapper;

    //Get all tickets
    public List<TicketResponse> getTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toTicketResponse).toList();
    }

    //Get ticket by id
    public TicketResponse getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));
        return ticketMapper.toTicketResponse(ticket);
    }

    //Create ticket
    public TicketResponse createTicket(TicketRequest request) {
        Ticket ticket = ticketMapper.toTicket(request);
        ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    //Update ticket
    public TicketResponse updateTicket(Long id, TicketRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));

        ticketMapper.updateTicket(request, ticket);
        ticketRepository.save(ticket);
        return ticketMapper.toTicketResponse(ticket);
    }

    //Delete ticket
    public String deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new AppException(ErrorCode.TICKET_NOT_FOUND);
        }

        ticketRepository.deleteById(id);
        return "Delete ticket successfully";
    }
}
