package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.BookingRequest;
import com.quynhtien.cineasy.dto.response.BookingResponse;
import com.quynhtien.cineasy.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "user", ignore = true)
    Booking toBooking(BookingRequest request);

    BookingResponse toBookingResponse(Booking booking);

    @Mapping(target = "tickets", ignore = true)
    void updateBooking(BookingRequest request, @MappingTarget Booking booking);
}

