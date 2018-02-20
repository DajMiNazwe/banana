package com.banana.shared;

import com.banana.model.TransactionEntity;
import com.banana.model.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse map(TransactionEntity entity) {
        TransactionResponse dto = new TransactionResponse();
        dto.setType(entity.getType());
        dto.setValue(entity.getValue());
        return dto;
    }
}
