package com.chargePoint.models;
/**
 * Access of an entity ID. This is used for retreival of ACL response from DB.
 */
public enum Access {
  // Access is allowed for entity
  ALLOWED,
  // Access is denied for entity
  DENIED
}