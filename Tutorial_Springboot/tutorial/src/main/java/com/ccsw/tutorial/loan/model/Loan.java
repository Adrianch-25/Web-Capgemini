package com.ccsw.tutorial.loan.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nameGame", nullable = false)
    private String nameGame;

    @Column(name = "nameClient", nullable = false)
    private String nameClient;

    @Column(name = "loanDate", nullable = false)
    private Date loanDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;


    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return nameClient
     */
    public String getNameClient() {

        return this.nameClient;
    }

    /**
     * @param nameClient new value of {@link #getNameClient}.
     */
    public void setNameClient(String nameClient) {

        this.nameClient = nameClient;
    }

    /**
     * @return nameGame
     */
    public String getNameGame() {

        return this.nameGame;
    }

    /**
     * @param nameGame new value of {@link #nameGame}.
     */
    public void setNameGame(String nameGame) {

        this.nameGame = nameGame;
    }
    /**
     * @return loanDate
     */
    public Date getLoanDate() {

        return this.loanDate;
    }

    /**
     * @param loanDate new value of {@link #loanDate}.
     */
    public void setLoanDate(Date loanDate) {

        this.loanDate = loanDate;
    }
    /**
     * @return endDate
     */
    public Date getEndDate() {

        return this.endDate;
    }

    /**
     * @param endDate new value of {@link #endDate}.
     */
    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }
}
