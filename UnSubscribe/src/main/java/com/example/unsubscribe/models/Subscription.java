package com.example.unsubscribe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Entity(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productLink;
    private Double price;
    private LocalDate expirationDate;
    private Integer timeInterval;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

}
