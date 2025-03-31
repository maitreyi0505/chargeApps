package com.chargePoint.util;

/**
 * This is a POJO used internally for handling incoming request validation & exposing validation errors, if any.
 */
public class ValidationResponse {
  boolean isRequestValid;
  String message;

  public ValidationResponse(boolean isRequestValid, String message) {
    this.isRequestValid = isRequestValid;
    this.message = message;
  }

  public boolean isRequestValid() {
    return isRequestValid;
  }

  public String getMessage() {
    return message;
  }

}
