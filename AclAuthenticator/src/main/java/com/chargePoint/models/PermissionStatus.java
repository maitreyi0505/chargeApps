package com.chargePoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Computed status of an ACL request
 */
public enum PermissionStatus {

  // The request has all the required permissions
  @JsonProperty("allowed")
  ALLOWED,

  // The request does not have all the required permissions
  @JsonProperty("not_allowed")
  NOT_ALLOWED,

  // The requested permissions could not retreived
  @JsonProperty("unknown")
  UNKNOWN,

  // The requested permissions are invalid, thus couldn't be resolved
  @JsonProperty("invalid")
  INVALID
}
