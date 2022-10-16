package com.reto.reto3.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.reto3.Repository.ReservationRepository;
import com.reto.reto3.model.Reservation;
import com.reto.reto3.model.DTOs.CompletedAndCancelled;
import com.reto.reto3.model.DTOs.TotalAndClient;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    // creamos los servicios

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationRepository.getAll();

    }

    public Optional<Reservation> getIdReservation(int id) {
        return reservationRepository.getIdReservation(id);
    }

    // Guardas datos
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);

        } else {
            Optional<Reservation> reservationEncontrado = getIdReservation(reservation.getIdReservation());
            if (reservationEncontrado.isEmpty()) {
                return reservationRepository.save(reservation);

            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {

        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservationEncontrado = getIdReservation(reservation.getIdReservation());
            if (!reservationEncontrado.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    reservationEncontrado.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reservationEncontrado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    reservationEncontrado.get().setStatus(reservation.getStatus());
                }
                return reservationRepository.save(reservationEncontrado.get());
            }
        }
        return reservation;

    }

    public boolean delete(int id) {
        Boolean respuesta = getIdReservation(id).map(elemento -> {
            reservationRepository.delete(elemento);
            return true;
        }).orElse(false);

        return respuesta;
    }

    public List<Reservation> getReservationsBetweenDatesResport(String fechaA, String fechaB, ReservationRepository reservationRepository2, ReservationRepository reservationRepository3) {
        SimpleDateFormat parser = new SimpleDateFormat("yyy-MM-dd");

        Date a = new Date();
        Date b = new Date();
        try {
            a = parser.parse(fechaA);
            b = parser.parse(fechaB);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        if (a.before(b)) {
            return reservationRepository.getReservationsBetweenDates(a, b);
        } else {
            return new ArrayList<>();
        }
    }

    public CompletedAndCancelled getReservationStatusReport(){
        List<Reservation> completed = reservationRepository.getReservationsByStatus("completed");
        List<Reservation> cancelled =  reservationRepository.getReservationsByStatus("concelled");

        int cantidadCompletadas = completed.size();
        int cantidadCanceladas = cancelled.size();

        return new CompletedAndCancelled((long) cantidadCompletadas, (long) cantidadCanceladas);
    }

    public List<TotalAndClient> getTopClientsReport(){
        return reservationRepository.getTopClients();
    }

    public List<Reservation> getReservationsBetweenDatesResport(String fecha1, String fecha2) {
        return null;
    }

    

}
