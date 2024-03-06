package com.dbproject.services;

import com.dbproject.Models.Credentials;
import com.dbproject.Models.OrderModel;
import com.dbproject.Models.RestaurantModel;
import com.dbproject.dto.Orders;
import com.dbproject.repository.CredentailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CancellationException;

//all logical services here
@Service
public class LoginService {

    CredentailRepository credentailRepository;

    @Autowired
    public LoginService(CredentailRepository credentailRepository) {
        this.credentailRepository = credentailRepository;

    }


    public Credentials getCredentialsByUsername(String username) {

        return credentailRepository.findByUsername(username);
    }

//    public List<Credentials> getAllCredentials(){
//
//        return credentailRepository.findAll();
//    }

    public List<Orders> getUserOrders(String username) {
        Credentials credentials = credentailRepository.findByUsername(username);
        if (credentials != null) {
            return credentailRepository.userorders(credentials);
        } else {
            // Handle the case where the user is not found
            return null;
        }
    }

    public Credentials authenticate(String username, String password) {
        return credentailRepository.findByUsernameAndPassword(username, password);
    }

    public int getid(String username) {
        Credentials credentials = credentailRepository.findByUsername(username);
        return credentials.getCid();
    }

    public Credentials registerUser(String username, String password, String email) {
        if (credentailRepository.existsByUsername(username)) {
            // Username is not unique
            return null;
        }

        if (credentailRepository.existsByEmail(email)) {
            // Email is not unique
            return null;
        }
        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setEmail(email);
        credentials.setIsAdmin(false);

        return credentailRepository.save(credentials);
    }


    public Integer getuserIdByName(String username) {
        Credentials credentials = credentailRepository.findByUsername(username);
        return (credentials != null) ? credentials.getCid() : null;
    }



}
