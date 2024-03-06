package com.dbproject.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.util.List;

@Entity
@Table(name="restaurants",schema="public")
@Data
@NoArgsConstructor

public class RestaurantModel {
    //data models handled by db
    @Id //look into auto generation
    @Column(name="rid",unique = true)
    private Integer rid;

    @Column(name="rname",unique = true)
    private String name;

    @Column(name="rcity")
    private String city;

    @Column(name="rtype")
    private String type;

    @Column(name="rspeciality")
    private String speciality;


    @OneToMany(mappedBy = "restaurant")
    private List<MenuModel> menuItems;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<OrderModel> orders;


}
