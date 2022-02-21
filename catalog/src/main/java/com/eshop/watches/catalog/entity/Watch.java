package com.eshop.watches.catalog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "watch")
public class Watch {
  
  @Id
  @GeneratedValue
  private Long id;

  private String model;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Brand getBrand() {
    return brand;
  }
  public void setBrand(Brand brand) {
    this.brand = brand;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }

}
