package com.pyxis.petstore.controller;

import com.pyxis.petstore.Maybe;
import com.pyxis.petstore.domain.order.Order;
import com.pyxis.petstore.domain.order.OrderLog;
import com.pyxis.petstore.domain.order.OrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/receipts")
public class ReceiptsController {
    private final OrderLog orderLog;

    @Autowired
    public ReceiptsController(OrderLog orderLog) {
        this.orderLog = orderLog;
    }

    @RequestMapping(value = "/{orderNumber}", method = RequestMethod.GET)
    public String show(@PathVariable String orderNumber, Model model) {
        Maybe<Order> entry = orderLog.find(new OrderNumber(orderNumber));
        if (entry.exists()) model.addAttribute(entry.bare());
        return "receipts/show";
    }
}
