package com.example.AllEShop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "picture_url")
    private String picture_url;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;
}
