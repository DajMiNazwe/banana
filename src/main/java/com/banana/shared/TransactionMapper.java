package com.banana.shared;

import com.banana.model.TransactionDTO;
import com.banana.model.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDTO map(TransactionEntity entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setType(entity.getType());
        dto.setValue(entity.getValue());
        return dto;
    }
}
