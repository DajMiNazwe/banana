package com.banana.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionDTO {

    private TransactionEntity.TransactionType type;

    private Long value;
}
