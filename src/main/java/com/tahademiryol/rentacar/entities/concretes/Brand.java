package com.tahademiryol.rentacar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// code first
@Entity // not needed but it's useful for reviewing
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id // Primary Key -> PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // @JsonBackReference
    @OneToMany(mappedBy = "brand")
    private List<Model> models;
}
