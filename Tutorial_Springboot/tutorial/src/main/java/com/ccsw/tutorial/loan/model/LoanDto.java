package com.ccsw.tutorial.loan.model;

import java.util.Date;

public class LoanDto {
    private Long id;
    private String nameClient;
    private String nameGame;
    private Date loanDate;
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
