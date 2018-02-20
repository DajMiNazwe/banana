package com.banana.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String ownerId;

    private TransactionType type;

    private Double value;

    private Instant operationDate;

    public enum TransactionType {
        INCREASE,
        DECREASE
    }
}
