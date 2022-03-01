package com.eshop.watches.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "watch_order")
public class WatchOrder extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private Long id;
  private String detail;
  @Column(name = "shipping_code")
  private String shippingCode;
  @Column(name = "shipping_status")
  private String shippingStatus;

  public WatchOrder() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShippingStatus() {
    return shippingStatus;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getShippingCode() {
    return shippingCode;
  }

  public void setShippingCode(String shippingCode) {
    this.shippingCode = shippingCode;
  }

  public void setShippingStatus(String shippingStatus) {
    this.shippingStatus = shippingStatus;
  }

}
