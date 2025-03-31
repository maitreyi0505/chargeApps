package com.chargePoint.util;

/**
 * Types of status exposed back to API caller based on response.
 */
public enum ResponseStatus {
  // Bad request due to invalid inputs
  BAD_REQUEST,

  // Request is valid & was accepted by system
  ACCEPTED,

  // Valid request, but failed due to internal error
  INTERNAL_ERROR
}
