package com.reto.reto3.Repository.CrudRepository;

import java.util.*;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.reto.reto3.model.Reservation;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    /* Reporte 1
     * SELECT * FROM Reservation WHERE starDate AFTER fechaA AND devolutionDate BEFORE fechaB
     * 
     */

     public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore(Date fechaA,  Date fechaB);
     
     /*Reporte 2
       * SELECT * FROM Reservation WHERE status = "valorQueNecesito";
      * 
      */
      public List<Reservation> findAllByStatus(String status);

      /*** Reporte 3
       * SELECT COUNT(Client), client FROM Reservation GROUP BY client ORDER BY COUNT(client) DESC;
       * 
       */
      @Query("ELECT c.client, COUNT(c.client), FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client) DESC")
      public List<Object[]> getTotalReservationsByClient();
    
}
