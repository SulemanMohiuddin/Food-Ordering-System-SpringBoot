package com.dbproject.controllers;// Controller class

import com.dbproject.Models.OrderModel;
import com.dbproject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Other methods...

    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam Integer orderId, @RequestParam String newStatus) {
        orderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/allOrders"; // Redirect to the order list page
    }

    @GetMapping("/allOrders")
    public String viewAllOrders(Model model) {
        List<OrderModel> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "allOrders";
    }

    @PostMapping("/startDelivery/{orderId}")
    public String startDelivery(@PathVariable Integer orderId) {
        orderService.startDelivery(orderId);
        return "redirect:/allOrders";
    }

    @PostMapping("/restaurantOrders/{rid}")
    public String ordersByRestaurant(@PathVariable Integer rid, Model model) {
        List<OrderModel> orders = orderService.getOrdersByRestaurant(rid);
        model.addAttribute("orders", orders);
        return "restaurantOrders";

    }


    @PostMapping("/changeOrderStatus/{orderId}")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Integer orderId) {
        orderService.startPreparing(orderId);
        return ResponseEntity.ok("Order status changed successfully");
    }


}
