package com.chargePoint.util;

import com.chargePoint.models.Access;
import com.chargePoint.models.ChargeSessionAcl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility that provides a list of sample charge session ACLs to be ingested in the ACL table during boot time.
 */
public class SampleDataProvider {

  public static List<ChargeSessionAcl> getSampleChargeSessionAcls() {
    ChargeSessionAcl chargeSessionAcl1 =
        new ChargeSessionAcl("123e4567-e89b-12d3-a456-426614174000", ChargeSessionAcl.EntityType.STATION,
            Access.ALLOWED);
    ChargeSessionAcl chargeSessionAcl2 =
        new ChargeSessionAcl("32378f81-8a51-4731-8d91-1a3ff26215df", ChargeSessionAcl.EntityType.STATION,
            Access.DENIED);
    ChargeSessionAcl chargeSessionAcl3 =
        new ChargeSessionAcl("maitreyi_sondhi_12345", ChargeSessionAcl.EntityType.DRIVER,
            Access.ALLOWED);
    ChargeSessionAcl chargeSessionAcl4 =
        new ChargeSessionAcl("someone_else_1234567890", ChargeSessionAcl.EntityType.DRIVER,
            Access.DENIED);

    return new ArrayList<>(Arrays.asList(chargeSessionAcl1, chargeSessionAcl2, chargeSessionAcl3, chargeSessionAcl4));
  }
}
