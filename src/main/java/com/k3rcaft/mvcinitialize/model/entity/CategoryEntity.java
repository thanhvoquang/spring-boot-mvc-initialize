package com.k3rcaft.mvcinitialize.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryEntity extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "urlImage")
    private String urlImage;
}
