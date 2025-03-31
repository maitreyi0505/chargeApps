package com.chargePoint.models;

/**
 * Model for the immediate API response of initializing charge session.
 */
public class ChargeSessionResponse {

  // Response status for the request
  private String status;

  // Message containing details related to the response status
  private String message;

  public void setStatus(String status) {
    this.status = status;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ChargeSessionResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}