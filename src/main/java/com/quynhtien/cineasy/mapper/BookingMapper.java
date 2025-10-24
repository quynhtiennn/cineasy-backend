package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.BookingRequest;
import com.quynhtien.cineasy.dto.response.BookingResponse;
import com.quynhtien.cineasy.entity.Booking;
import com.quynhtien.cineasy.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {
    TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "user", ignore = true)
    Booking toBooking(BookingRequest request);

    @Mapping(source ="tickets", target = "ticketResponses")
    default BookingResponse toBookingResponse(Booking booking){
        return BookingResponse.builder()
                .id(String.valueOf(booking.getId()))
                .bookingTime(booking.getBookingTime())
                .totalPrice(booking.getTotalPrice())
                .movieTitle(booking.getTickets().getFirst().getShowtime().getMovie().getTitle())
                .showtimeStartTime(booking.getTickets().getFirst().getShowtime().getStartTime())
                .bookingStatusEnum(booking.getBookingStatusEnum())
                .ticketResponses(booking.getTickets().stream()
                        .map(ticketMapper::toTicketResponse)
                        .toList())
                .build();
    }

    @Mapping(target = "tickets", ignore = true)
    void updateBooking(BookingRequest request, @MappingTarget Booking booking);
}

