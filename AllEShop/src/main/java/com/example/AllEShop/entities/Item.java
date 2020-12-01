package com.example.AllEShop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "t_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "description",length = 200)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "stars")
    private int stars;

    @Column(name = "small_picurl")
    private String smallPicURL;

    @Column(name = "large_picurl")
    private String largePicURL;

    @Column(name = "added_date")
    private Date addedDate;

    @Column(name = "in_top_page")
    private Boolean inTopPage;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

}
