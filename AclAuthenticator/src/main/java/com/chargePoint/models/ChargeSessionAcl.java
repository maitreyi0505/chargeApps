package com.chargePoint.models;

import lombok.Data;

/**
 * Data model of the ACL for storing charge sessions
 */
@Data
public class ChargeSessionAcl {

  // Identifier of the entity, like station ID, driver ID etc. for whom access has been defined
  String entityId;

  // Type of the entity for which the ACL is stored
  EntityType entityType;

  // Value of the provided access to this entity
  Access access;

  public enum EntityType {
    DRIVER,STATION
  }


  public ChargeSessionAcl() {
  }

  public ChargeSessionAcl(String entityId, EntityType entityType, Access access) {
    this.entityId = entityId;
    this.entityType = entityType;
    this.access = access;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityType entityType) {
    this.entityType = entityType;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public Access getAccess() {
    return access;
  }

  public void setAccess(Access access) {
    this.access = access;
  }

  @Override
  public String toString() {
    return "ChargeSessionAcl{" + "entityId='" + entityId + '\'' + ", entityType='" + entityType + '\'' + ", access='"
        + access + '\'' + '}';
  }
}