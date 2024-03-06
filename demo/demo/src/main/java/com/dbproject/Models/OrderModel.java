package com.dbproject.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.util.List;

@Entity
@Table(name="order",schema="public")
@Data
@NoArgsConstructor

public class OrderModel {
    //data models handled by db
    @Id //look into auto generation
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="oid",unique = true)
    private Integer orderid;

    @Column(name="totalprice")
    private Double totalprice;

    @Column(name="status")
    private String status;


    @ManyToOne
    @JoinColumn(name="rid")  // Name of the foreign key column
    private RestaurantModel restaurant;

    @ManyToOne
    @JoinColumn(name="cid")  // Name of the foreign key column
    private Credentials credentials;

    @Column(name="oitems")
    private String oitems;

}
