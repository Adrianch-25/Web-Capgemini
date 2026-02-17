package com.ccsw.tutorial.customer.model;

import jakarta.persistence.*;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

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
     * @param name new value of {@link #getId}
     */
    public void setName(String name){
        this.name = name;
    }
}
