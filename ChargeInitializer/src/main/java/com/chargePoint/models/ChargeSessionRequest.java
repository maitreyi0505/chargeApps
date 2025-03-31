package com.chargePoint.models;

public class ChargeSessionRequest {

  // UUID identifier of the station
  private String stationId;
  private String driverId;
  private String callbackUrl;

  public ChargeSessionRequest(String stationId, String driverId, String callbackUrl) {
    this.stationId = stationId;
    this.driverId = driverId;
    this.callbackUrl = callbackUrl;
  }

  public String getStationId() {
    return stationId;
  }

  public String getDriverId() {
    return driverId;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }
}