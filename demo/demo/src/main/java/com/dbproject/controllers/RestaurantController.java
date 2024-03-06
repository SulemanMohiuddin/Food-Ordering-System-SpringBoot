package com.dbproject.controllers;

import com.dbproject.Models.OrderModel;
import com.dbproject.Models.RestaurantModel;
import com.dbproject.services.OrderService;
import com.dbproject.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RestaurantController {
    private final RestaurantService restaurantService;
    private  final OrderService orderService;
    public String name;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, OrderService orderService) {
        this.restaurantService = restaurantService;
        this.orderService = orderService;
    }

    @GetMapping("/restaurants")
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());

        return "restaurants"; // Assuming you have a Thymeleaf template named "restaurantList.html"
    }

    @GetMapping("/login2")
    public String loginPage(Model model) {
        return "login2";
    }

    @PostMapping("/restaurantOrders")
    public String restaurantOrders(@RequestParam Integer id, @RequestParam String username, HttpSession session, Model model) {
        RestaurantModel user = restaurantService.rest_login(id, username);

        if (user != null) {
            // Successful login
            session.setAttribute("loggedInRestaurantId", id);  // Store restaurant ID in session
            model.addAttribute("user", user);
            model.addAttribute("name", user.getName());
            List<OrderModel> orders = orderService.getOrdersByRestaurant(id);
            model.addAttribute("orders", orders);
            return "restaurantOrders";
        } else {
            // Failed login
            model.addAttribute("error", "Invalid username or password");
            return "login2"; // Redirect back to the login page with an error message
        }
    }



}
