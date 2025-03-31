package com.chargePoint.util;

import com.chargePoint.models.ChargeSessionRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SessionRequestValidator {

  static boolean isChargeSessionValid(ChargeSessionRequest request) {
    return isDriverIdValid(request.getDriverId()) && isUrlValid(request.getCallbackUrl());
  }

  private static boolean isUrlValid(String callbackUrl) {
    return true;
  }

  private static boolean isDriverIdValid(String driverId) {
    Pattern pattern = Pattern.compile("A-Z", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(driverId);
    boolean matchFound = matcher.find();
    return (driverId.length() >= 20 && driverId.length() <= 80 && matchFound);
  }
}
