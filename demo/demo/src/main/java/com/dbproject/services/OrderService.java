package com.dbproject.services;

import com.dbproject.Models.Credentials;
import com.dbproject.Models.OrderModel;
import com.dbproject.Models.RestaurantModel;
import com.dbproject.repository.CredentailRepository;
import com.dbproject.repository.OrderRepository;
import com.dbproject.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final CredentailRepository credentailRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, CredentailRepository credentailRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.credentailRepository = credentailRepository;
    }

    public OrderModel createOrder(Double totalprice, String status, Integer rid, Integer cid, String oitems) {
        try {
            RestaurantModel restaurant = restaurantRepository.findByrid(rid);
            Credentials customer = credentailRepository.findByCid(cid);

            if (restaurant != null && customer != null) {
                OrderModel order = new OrderModel();
                order.setTotalprice(totalprice);
                order.setStatus(status);
                order.setRestaurant(restaurant);
                order.setCredentials(customer);
                order.setOitems(oitems);

                return orderRepository.save(order);
            } else {
                // Handle invalid restaurant or customer
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }

//    public Iterable<OrderModel> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public void saveOrder(OrderModel orderModel) {
//        orderRepository.save(orderModel);
//    }


    public List<OrderModel> getOrdersByCredentials(String username) {
        Credentials credentials = credentailRepository.findByUsername(username);
        if (credentials != null) {
            return orderRepository.findAllByCredentials(credentials);
        } else {
            // Handle the case where the user is not found
            return null;
        }
    }

    public void updateOrderStatus(Integer orderId, String newStatus) {
        Optional<OrderModel> optionalOrder = orderRepository.findById(orderId);
        optionalOrder.ifPresent(order -> {
            order.setStatus(newStatus);
            orderRepository.save(order);
        });
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public void startDelivery(Integer orderId) {
        updateOrderStatus(orderId, "Delivering");
    }

    @Scheduled(fixedDelay = 300000) // 5 minutes in milliseconds
    public void scheduleDeliveryCompletion(Integer orderId) {
        // Automatically update order status to "Delivered" after 5 minutes
        updateOrderStatus(orderId, "Delivered");
    }

    public List<OrderModel> getOrdersByRestaurant(Integer rid) {
        return orderRepository.findByRestaurant_Rid(rid);
    }

    public void startPreparing(Integer orderId) {
        updateOrderStatus(orderId, "Preparing");
        }




    public OrderModel getOrderById(Integer orderId) {
        Optional<OrderModel> optionalOrder = orderRepository.findById(orderId);

        return optionalOrder.orElse(null);
    }

}
