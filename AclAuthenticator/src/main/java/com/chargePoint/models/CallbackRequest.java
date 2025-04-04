package com.chargePoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CallbackRequest {
  @JsonProperty("station_id")
  String stationId;
  @JsonProperty("driver_token")
  String driverToken;
  PermissionStatus status;

  public CallbackRequest(String stationId, String driverToken, PermissionStatus status) {
    this.stationId = stationId;
    this.driverToken = driverToken;
    this.status = status;
  }

  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getDriverToken() {
    return driverToken;
  }

  public void setDriverToken(String driverToken) {
    this.driverToken = driverToken;
  }

  public PermissionStatus getStatus() {
    return status;
  }

  public void setStatus(PermissionStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "CallbackRequest{" + "stationId='" + stationId + '\'' + ", driverToken='" + driverToken + '\'' + ", status="
        + status + '}';
  }
}

