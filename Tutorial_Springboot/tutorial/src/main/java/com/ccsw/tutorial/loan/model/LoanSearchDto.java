package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

import java.util.Date;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */

public class LoanSearchDto {

    private PageableRequest pageable;

    private String gameTitle;
    private String clientName;
    private Date date;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
