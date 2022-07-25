package com.tads.mhsf.restaurant.controllers;

import com.tads.mhsf.restaurant.entities.PaymentMethod;
import com.tads.mhsf.restaurant.exceptions.RepositoryException;
import com.tads.mhsf.restaurant.services.PaymentMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @RequestMapping("/payment/register")
    public String registerPaymentMethodForm() {
        return "register_payment";
    }

    @RequestMapping("/payment/register/confirm")
    public String registerPaymentMethod(RedirectAttributes redirectAttributes, PaymentMethod paymentMethod) {
        try {
            paymentMethodService.createPaymentMethod(paymentMethod);
            redirectAttributes.addFlashAttribute("msg", "Payment method created successfully!");
        } catch (RepositoryException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
        }
        return "redirect:/adm/home";
    }

    @RequestMapping("/order/{dishID}/payment")
    public String getPaymentMethods(@PathVariable("dishID") int dishID,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        try {
            List<PaymentMethod> paymentMethods = paymentMethodService.findAllPaymentMethods();
            model.addAttribute("dishID", dishID);
            model.addAttribute("paymentMethods", paymentMethods);
            return "select_payment";
        } catch (RepositoryException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:/dishes";
        }

    }

    @RequestMapping("/payment/{dishID}/select")
    public String selectPaymentMethod(@PathVariable("dishID") int dishID,
                                      RedirectAttributes redirectAttributes,
                                      int paymentID) {
        redirectAttributes.addFlashAttribute("paymentID", paymentID);
        redirectAttributes.addFlashAttribute("dishID", dishID);
        return "redirect:/order/confirm";
    }

}
