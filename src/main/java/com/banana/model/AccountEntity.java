package com.banana.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String ownerId;

    private Double balance;
}
