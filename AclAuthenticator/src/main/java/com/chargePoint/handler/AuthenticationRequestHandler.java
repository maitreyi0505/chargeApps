package com.chargePoint.handler;

import com.chargePoint.dataManager.AclResponseAuditDAO;
import com.chargePoint.dataManager.ChargeSessionAclDAO;
import com.chargePoint.models.Access;
import com.chargePoint.models.AclResponseAudit;
import com.chargePoint.models.PermissionStatus;
import com.chargePoint.models.CallbackRequest;
import com.chargePoint.models.ChargeSessionAcl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationRequestHandler {
  @Autowired
  private ChargeSessionAclDAO _chargeSessionAclDAO;
  @Autowired
  private AclResponseAuditDAO _aclResponseAuditDAO;
  @Autowired
  private CallbackHandler _callbackHandler;

  private static final Logger _log = LoggerFactory.getLogger(AuthenticationRequestHandler.class);

  /** Method to handle an ACL permission request. Steps involved -
   * 1. Read ACLs for station ID & driver ID both.
   * 2. Compute permission based on these ACLs on the following basis -
   *    a.  UNKNOWN - If ACL read throws a timeout exception
   *    b.  ALLOWED - If ACL is valid for both IDs
   *    c.  NOT_ALLOWED - If ACL is not valid for at least 1 ID
   *    d.  INVALID - If ACLs returned is not of size 2
   * 3. Send the permission response to callback URL.
   * 4. Store the response in a tracking table for audit purpose.
   */
  public void handleAclRequest(String driverId, String stationId, String callbackUrl, long timestamp) {

    PermissionStatus permissionStatus;
    try {
      List<ChargeSessionAcl> acls = _chargeSessionAclDAO.getChargeSessionAclsForEntityPair(driverId, stationId);
      permissionStatus = computePermissionStatus(acls);
    } catch (QueryTimeoutException ex) {
      permissionStatus = PermissionStatus.UNKNOWN;
    }
    _log.info("Permission Status computed for the request: " + permissionStatus.name());

    CallbackRequest callbackRequest = new CallbackRequest(stationId, driverId, permissionStatus);
    int responseCode = _callbackHandler.sendResponseToCallback(callbackUrl, callbackRequest);

    AclResponseAudit aclResponseAudit = new AclResponseAudit(stationId, driverId, callbackUrl, timestamp,
        permissionStatus, responseCode);

    _log.info("Audit created for this request: " + aclResponseAudit.toString());
    _aclResponseAuditDAO.insertAclResponseAudit(aclResponseAudit);
  }

  private PermissionStatus computePermissionStatus(List<ChargeSessionAcl> acls) {
    if(acls.size() != 2) {
      return PermissionStatus.INVALID;
    } else if (acls.getFirst().getAccess().equals(Access.ALLOWED) &&
        acls.getLast().getAccess().equals(Access.ALLOWED)) {
      return PermissionStatus.ALLOWED;
    } else {
      return PermissionStatus.NOT_ALLOWED;
    }
  }

}
