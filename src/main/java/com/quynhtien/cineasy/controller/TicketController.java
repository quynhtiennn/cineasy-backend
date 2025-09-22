package com.quynhtien.cineasy.controller;

import com.quynhtien.cineasy.dto.request.TicketRequest;
import com.quynhtien.cineasy.dto.response.ApiResponse;
import com.quynhtien.cineasy.dto.response.ShowTimeResponse;
import com.quynhtien.cineasy.dto.response.TicketResponse;
import com.quynhtien.cineasy.service.TicketService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {
    TicketService ticketService;

    //Get all tickets
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<TicketResponse>> findAll() {
        return ApiResponse.<List<TicketResponse>>builder()
                .result(ticketService.getTickets())
                .build();
    }

    //Get ticket by id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<TicketResponse> findAll(@PathVariable Long id) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.getTicket(id))
                .build();
    }

    //Create ticket
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<TicketResponse> createTicket(@RequestBody @Valid TicketRequest request) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.createTicket(request))
                .build();
    }

    //Update ticket
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<TicketResponse> updateTicket(@PathVariable Long id, @RequestBody @Valid TicketRequest request) {
        return ApiResponse.<TicketResponse>builder()
                .result(ticketService.updateTicket(id, request))
                .build();
    }

    //Delete ticket
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteTicket(@PathVariable Long id) {
        return ApiResponse.<String>builder()
                .result(ticketService.deleteTicket(id))
                .build();
    }
}
