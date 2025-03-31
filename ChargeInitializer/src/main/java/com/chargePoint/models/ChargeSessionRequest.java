package com.chargePoint.models;

/**
 * Model for incoming API request for initializing charge session.
 */
public class ChargeSessionRequest {

  // UUID identifier of the station from where charge request is being made
  private String stationId;

  // Identifier token for the driver initializing charge request
  private String driverId;

  // Callback URL for sending out the response of authorization check
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