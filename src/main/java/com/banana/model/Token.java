package com.banana.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private String userId;

    private Instant creationTime = Instant.now();
}
