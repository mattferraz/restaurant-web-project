package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.model.entities.PaymentMethod;
import com.tads.mhsf.restaurant.model.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PaymentMethodController {

    @RequestMapping("/payment/register")
    public String registerPaymentMethodForm(Model model) {
        return "register_payment";
    }

    @RequestMapping("/payment/register/confirm")
    public String registerPaymentMethod(Model model, PaymentMethod paymentMethod) {
        new PaymentMethodRepository().create(paymentMethod);
        model.addAttribute("msg", "Método de Pagamento criado com sucesso!");
        return "redirect:/home";
    }

    @RequestMapping("/order/{dishID}/payment")
    public String getPaymentMethod(Model model, @PathVariable("dishID") int id) {
        List<PaymentMethod> paymentMethods = new PaymentMethodRepository().readAll();
        model.addAttribute("dishID", id);
        model.addAttribute("paymentMethods", paymentMethods);

        return "select_payment";
    }

}
