package com.dbproject.repository;

import com.dbproject.Models.Credentials;
import com.dbproject.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Integer> {

    @Query("SELECT o FROM OrderModel o WHERE o.credentials = :credentials")
    List<OrderModel> findAllByCredentials(@Param("credentials") Credentials credentials);

    List<OrderModel> findByRestaurant_Rid(Integer rid);

    List<OrderModel> findByOrderid(Integer id);

}
