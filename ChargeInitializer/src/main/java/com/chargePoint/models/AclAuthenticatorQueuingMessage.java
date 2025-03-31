package com.chargePoint.models;

/**
 * Model for the event sent to ACL Authenticator service, containing details of the charge request session.
 */
public class AclAuthenticatorQueuingMessage {
  // UUID identifier of the station from where charge request is being made
  private String stationId;

  // Identifier token for the driver initializing charge request
  private String driverId;

  // Callback URL for sending out the response of authorization check
  private String callbackUrl;

  // Timestamp (in UTC) for request creation in millis
  private long timestamp;

  public AclAuthenticatorQueuingMessage(String stationId, String driverId, String callbackUrl) {
    this.stationId = stationId;
    this.driverId = driverId;
    this.callbackUrl = callbackUrl;
    this.timestamp = System.currentTimeMillis();
  }

  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "{" + "stationId='" + stationId + '\'' + ", driverId='" + driverId + '\''
        + ", callbackUrl='" + callbackUrl + '\'' + ", timestamp=" + timestamp + '}';
  }
}
