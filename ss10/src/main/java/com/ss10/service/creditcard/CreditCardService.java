package com.ss10.service.creditcard;

import com.ss10.model.dto.request.CreditCardDTO;
import com.ss10.model.entity.CreditCard;

import java.util.UUID;

public interface CreditCardService{
    CreditCard create(CreditCardDTO dto);
    CreditCard updateLimit(UUID id, CreditCardDTO dto);
    CreditCard updateStatus(UUID id, CreditCardDTO dto);
}
