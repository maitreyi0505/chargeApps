package com.chargePoint.models;

import lombok.Data;

/**
 * Data model of a request's audit data persisted for tracking
 */
@Data
public class AclResponseAudit {
  // UUID identifier of the station from where charge request is being made
  String stationId;

  // Identifier token for the driver initializing charge request
  String driverId;

  // Callback URL for sending out the response of authorization check
  String callbackUrl;

  // Timestamp (in UTC) for request creation in millis
  long timestamp;

  // Computed status of an ACL request
  PermissionStatus permissionStatus;

  // Response status from the callback URL called
  int callbackResponseStatus;

  public AclResponseAudit(String stationId, String driverId, String callbackUrl, long timestamp, PermissionStatus permissionStatus,
      int callbackResponseStatus) {
    this.stationId = stationId;
    this.driverId = driverId;
    this.callbackUrl = callbackUrl;
    this.timestamp = timestamp;
    this.permissionStatus = permissionStatus;
    this.callbackResponseStatus = callbackResponseStatus;
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

  public PermissionStatus getPermissionStatus() {
    return permissionStatus;
  }

  public void setPermissionStatus(PermissionStatus permissionStatus) {
    this.permissionStatus = permissionStatus;
  }

  public int getCallbackResponseStatus() {
    return callbackResponseStatus;
  }

  public void setCallbackResponseStatus(int callbackResponseStatus) {
    this.callbackResponseStatus = callbackResponseStatus;
  }

  @Override
  public String toString() {
    return "AclResponseAudit{" + "stationId='" + stationId + '\'' + ", driverId='" + driverId + '\'' + ", callbackUrl='"
        + callbackUrl + '\'' + ", timestamp=" + timestamp + ", permissionStatus=" + permissionStatus
        + ", callbackResponseStatus=" + callbackResponseStatus + '}';
  }
}
