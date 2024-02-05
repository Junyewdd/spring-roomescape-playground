package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationQueryingDAO;
import roomescape.dao.ReservationUpdatingDAO;
import roomescape.dao.TimeQueryingDAO;
import roomescape.dao.TimeUpdatingDAO;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.dto.ReservationAddRequest;
import roomescape.dto.ReservationDTO;

@Service
public class ReservationService {
    ReservationQueryingDAO reservationQueryingDAO;

    ReservationUpdatingDAO reservationUpdatingDAO;


    public ReservationService(final ReservationQueryingDAO reservationQueryingDAO, final ReservationUpdatingDAO reservationUpdatingDAO){

        this.reservationQueryingDAO = reservationQueryingDAO;
        this.reservationUpdatingDAO = reservationUpdatingDAO;

    }


    public List<ReservationDTO> findAllReservations() {
        return reservationQueryingDAO.getAllReservations();
    }

    public ReservationDTO bookReservation(ReservationAddRequest reservation) {

        Reservation newReservation = new Reservation();

        newReservation.setName(reservation.getName());
        newReservation.setDate(reservation.getDate());
        newReservation.setTime(new Time(reservation.getTime().getTime()));

        Number reservationId = reservationUpdatingDAO.save(newReservation);

        ReservationDTO savedReservation = ReservationDTO.toEntity(newReservation.getId(), newReservation.getName(), newReservation.getDate(), newReservation.getTime());

        return savedReservation;

    }

    public int delete(long id) {
        return reservationUpdatingDAO.delete(id);
    }
}
