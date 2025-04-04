package com.chargePoint.util;

import com.chargePoint.models.ChargeSessionRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SessionRequestValidator {

  /**
   * Perform validation checks on incoming request for session creation. This internally performs checks on all
   * input fields & provides error message to be passed back to client, if any.
   * @param chargeSessionRequest Identifier of charge session request
   * @return {@link ValidationResponse} tuple of final validation decision & reason for any failures.
   */
  public static ValidationResponse validateRequest(ChargeSessionRequest chargeSessionRequest) {
    if (!isDriverIdValid(chargeSessionRequest.getDriverId())) {
      return new ValidationResponse(false, Constants.DRIVER_ID_INVALID_MESSAGE);
    } else if (!isUrlValid(chargeSessionRequest.getCallbackUrl())) {
      return new ValidationResponse(false, Constants.CALLBACK_URL_INVALID_MESSAGE);
    } else if (!isStationIDValid(chargeSessionRequest.getStationId())) {
      return new ValidationResponse(false, Constants.STATION_ID_INVALID_MESSAGE);
    }
    return new ValidationResponse(true, Constants.VALID_REQUEST);
  }

  private static boolean isStationIDValid(String stationId) {
    try{
      return UUID.fromString(stationId).version() == 4;
    } catch (IllegalArgumentException ex){
      return false;
    }
  }

  private static boolean isDriverIdValid(String driverId) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9_\\-.]{20,80}", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(driverId);
    boolean matchFound = matcher.find();
    return (driverId.length() >= 20 && driverId.length() <= 80 && matchFound);
  }

  private static boolean isUrlValid(String callbackUrl) {
    try {
      new URL(callbackUrl);
    } catch (MalformedURLException ex) {
      return false;
    }
    return true;
  }

}
