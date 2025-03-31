package com.chargePoint.models;
import lombok.Data;

@Data
public class ChargeSessionAcl {
  String entityId;
  String entityType;
  String access;

  public ChargeSessionAcl() {
  }

  public ChargeSessionAcl(String access, String entityType, String entityId) {
    this.access = access;
    this.entityType = entityType;
    this.entityId = entityId;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public String getEntityType() {
    return entityType;
  }

  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  @Override
  public String toString() {
    return "ChargeSessionAcl{" + "entityId='" + entityId + '\'' + ", entityType='" + entityType + '\'' + ", access='"
        + access + '\'' + '}';
  }
}