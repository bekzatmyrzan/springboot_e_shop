package com.example.AllEShop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_pictures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "added_date")
    private Date added_date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
}
