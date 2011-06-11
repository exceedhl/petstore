package com.pyxis.petstore.domain.order;

import com.pyxis.petstore.domain.billing.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cashier implements CheckoutAssistant, PaymentCollector {
    private final OrderNumberSequence orderNumberSequence;
    private final OrderLog orderLog;

    @Autowired
    public Cashier(OrderNumberSequence orderNumberSequence, OrderLog orderLog) {
        this.orderNumberSequence = orderNumberSequence;
        this.orderLog = orderLog;
    }

    public Order checkout(Cart cart) {
        Order order = new Order(orderNumberSequence.nextOrderNumber());
        order.addItemsFrom(cart);
        cart.clear();
        return order;
    }

    public void collectPayment(Order order, PaymentMethod paymentMethod) {
        order.pay(paymentMethod);
        orderLog.record(order);
    }
}
