package com.eshop.watches.order.utils;

public enum ShippingState {

  Pending {
    @Override
    public ShippingState nextState() {
        return Created;
    }

    @Override
    public String getStateText() {
        return "PENDING CONFIRMATION";
    }
  },
  Created {
    @Override
    public ShippingState nextState() {
        return Delivering;
    }

    @Override
    public String getStateText() {
        return "SHIPMENT CREATED";
    }
  },
  Delivering {
    @Override
    public ShippingState nextState() {
        return Delivered;
    }

    @Override
    public String getStateText() {
        return "OUT FOR DELIVERY";
    }
  },
  Delivered {
    @Override
    public ShippingState nextState() {
        return Delivered;
    }

    @Override
    public String getStateText() {
        return "DELIVERED";
    }
  },
  Error {
    @Override
    public ShippingState nextState() {
        return Error;
    }

    @Override
    public String getStateText() {
        return "DELIVERY ERROR";
    }
  };

  public abstract ShippingState nextState(); 
  public abstract String getStateText();
  public static ShippingState getShippingState(String state){
    switch(state){
      case "PENDING CONFIRMATION": return ShippingState.Pending;
      case "SHIPMENT CREATED": return ShippingState.Created;
      case "OUT FOR DELIVERY": return ShippingState.Delivering;
      case "DELIVERED": return ShippingState.Delivered;
      default: return ShippingState.Error;
    }

  }
}
