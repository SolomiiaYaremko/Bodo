package com.solobodo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class EntertainmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "entertainmentType", fetch = FetchType.LAZY)
    private List<Offer> offer;

    public EntertainmentType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Offer> getOffer() {
        return offer;
    }

    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }
}
