package com.ccsw.tutorial.customer.model;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
public class CustomerDto {
    String name;

    Long id;

    /**
     * @return id
     */
    public Long getId(){

        return this.id;
    }

    /**
     * @return name
     */
    public String getName(){

        return this.name;
    }

    /**
     * @param id new value of {@link #getId}
     */
    public void setId(Long id){

        this.id = id;
    }
    /**
     * @param name new value of {@link #getName}
     */
    public void setName(String name){

        this.name = name;
    }
}
