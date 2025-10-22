package com.quynhtien.cineasy.mapper;

import com.quynhtien.cineasy.dto.request.ShowtimeRequest;
import com.quynhtien.cineasy.dto.response.ShowtimeResponse;
import com.quynhtien.cineasy.dto.response.ShowtimeSummaryResponse;
import com.quynhtien.cineasy.entity.Showtime;
import com.quynhtien.cineasy.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface ShowtimeMapper {
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "auditorium", ignore = true)
    Showtime toShowtime(ShowtimeRequest request);

    @Mapping(source ="auditorium.totalRows", target = "totalRows")
    @Mapping(source ="auditorium.seatsPerRow", target = "seatsPerRow")
    @Mapping(source ="movie.id", target = "movieId")
    @Mapping(source ="movie.title", target = "movieTitle")
    @Mapping(source ="auditorium.id", target = "auditoriumId")
    @Mapping(source ="auditorium.name", target = "auditoriumName")
    @Mapping(source ="tickets", target = "ticketResponses")
    ShowtimeResponse toShowtimeResponse(Showtime showtime);


    default ShowtimeSummaryResponse toShowtimeSummaryResponse(Showtime showtime){
        int availableCount = (int)showtime.getTickets().stream()
                .filter(Ticket::isAvailable)
                .count();
        double minPrice = showtime.getTickets().stream()
                .mapToDouble(Ticket::getPrice)
                .min()
                .orElse(0);

        double maxPrice = showtime.getTickets().stream()
                .mapToDouble(Ticket::getPrice)
                .max()
                .orElse(0);

        return ShowtimeSummaryResponse.builder()
                .id(showtime.getId())
                .startTime(showtime.getStartTime())
                .availableCount(availableCount)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
    }

    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "auditorium", ignore = true)
    void updateShowtime(ShowtimeRequest request, @MappingTarget Showtime showtime);
}

