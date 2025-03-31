package com.chargePoint.models;

public class AclAuthenticatorQueuingMessage {
  private String stationId;
  private String driverId;
  private String callbackUrl;

  public AclAuthenticatorQueuingMessage(String stationId, String driverId, String callbackUrl) {
    this.stationId = stationId;
    this.driverId = driverId;
    this.callbackUrl = callbackUrl;
  }

  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }
}
