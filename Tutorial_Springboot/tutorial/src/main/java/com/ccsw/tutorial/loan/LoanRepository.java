package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {

    Page<Loan> findAll(Pageable pageable);

    // Lista con préstamos activos para no permitir alquileres duplicadors
    @Query("""
        SELECT COUNT(l) FROM Loan l
        WHERE l.nameGame = :game
          AND l.loanDate <= :endDate
          AND l.endDate   >= :loanDate
    """)
    long countOverlappingByGame(
            @Param("game") String game,
            @Param("loanDate") Date loanDate,
            @Param("endDate")   Date endDate
    );

    // Lo mismo pero excluyendo el id (Para editarlo)
    @Query("""
        SELECT COUNT(l) FROM Loan l
        WHERE l.nameGame = :game
          AND l.id <> :id
          AND l.loanDate <= :endDate
          AND l.endDate   >= :loanDate
    """)
    long countOverlappingByGameExclude(
            @Param("id")   Long id,
            @Param("game") String game,
            @Param("loanDate") Date loanDate,
            @Param("endDate")   Date endDate
    );

    //Consulta para conta cuántos préstamos del cliente se solapan con el máximo permitido
    @Query("""
        SELECT COUNT(l) FROM Loan l
        WHERE l.nameClient = :client
          AND l.loanDate   <= :endDate
          AND l.endDate    >= :loanDate
    """)
    long countOverlappingByClient(
            @Param("client")   String client,
            @Param("loanDate") Date loanDate,
            @Param("endDate")  Date endDate
    );

    //La misma pero excluyendo el id para poder editar la consulta
    @Query("""
        SELECT COUNT(l) FROM Loan l
        WHERE l.nameClient = :client
          AND l.id <> :id
          AND l.loanDate   <= :endDate
          AND l.endDate    >= :loanDate
    """)
    long countOverlappingByClientExclude(
            @Param("id")       Long id,
            @Param("client")   String client,
            @Param("loanDate") Date loanDate,
            @Param("endDate")  Date endDate
    );
    //CONSULTA PARA LOS FILTROS IGUALANDO NOMBRES O SI SON NULOS TAMBIÉN SIRVE AUNQUE LOS HAYA VALIDADO
    @Query("""
    SELECT l FROM Loan l
    WHERE (:gameTitle IS NULL OR LOWER(l.nameGame) LIKE LOWER(CONCAT('%', :gameTitle, '%')))
      AND (:clientName IS NULL OR LOWER(l.nameClient) LIKE LOWER(CONCAT('%', :clientName, '%')))
      AND (:date IS NULL OR (l.loanDate <= :date AND l.endDate >= :date))
""")
    Page<Loan> searchLoans(
            @Param("gameTitle") String gameTitle,
            @Param("clientName") String clientName,
            @Param("date") Date date,
            Pageable pageable
    );

}



