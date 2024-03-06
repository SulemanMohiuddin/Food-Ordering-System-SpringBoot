package com.dbproject.services;

import com.dbproject.Models.OrderModel;
import com.dbproject.Models.RestaurantModel;
import com.dbproject.repository.OrderRepository;
import com.dbproject.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
    }

    public List<RestaurantModel> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    public Integer getRestaurantIdByName(String restaurantName) {
        RestaurantModel restaurant = restaurantRepository.findByName(restaurantName);
        return (restaurant != null) ? restaurant.getRid() : null;
    }

    public RestaurantModel rest_login(int id , String name){
        return restaurantRepository.findByRidAndAndName(id,name);

    }

    public List<OrderModel> getOrdersByRestaurantFromSession(HttpSession session) {
        Integer restaurantId = (Integer) session.getAttribute("loggedInRestaurantId");
        // Perform null check and handle appropriately
        return orderRepository.findByRestaurant_Rid(restaurantId);
    }
}
