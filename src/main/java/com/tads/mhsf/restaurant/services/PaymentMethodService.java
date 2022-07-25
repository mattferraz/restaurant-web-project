package com.tads.mhsf.restaurant.services;

import com.tads.mhsf.restaurant.entities.PaymentMethod;
import com.tads.mhsf.restaurant.exceptions.DataNotFoundException;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethod findPaymentMethodByID(int id) throws DataNotFoundException, RepositoryException {
        return paymentMethodRepository
                .read(id)
                .orElseThrow(() -> new DataNotFoundException("Payment method not found. Please, try again."));
    }

    public void createPaymentMethod(PaymentMethod paymentMethod) throws RepositoryException {
        paymentMethodRepository.create(paymentMethod);
    }

    public List<PaymentMethod> findAllPaymentMethods() throws RepositoryException {
        return paymentMethodRepository.readAll();
    }
}
