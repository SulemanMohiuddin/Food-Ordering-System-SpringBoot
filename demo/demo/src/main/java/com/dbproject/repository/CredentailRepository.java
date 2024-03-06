package com.dbproject.repository;

import com.dbproject.Models.Credentials;
import com.dbproject.dto.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentailRepository extends JpaRepository<Credentials,String> {
    Credentials findByUsername(String username);
    Credentials findByPassword(String password);
    Credentials findByEmail(String email);
    Credentials findByCid(Integer cid);

   // List<Credentials> findAll();
    Credentials findByUsernameAndPassword(String username , String password);

        boolean existsByUsername(String username);
        boolean existsByEmail(String email);


    @Query("SELECT o.credentials.username, o.oitems, o.status, o.totalprice FROM OrderModel o WHERE o.credentials = :credentials")
    public List<Orders> userorders(Credentials credentials);



}
