package com.chargePoint.util;

public class Constants {
  public static final String SUCCESS_MESSAGE =
      "Request is being processed asynchronously. The result will be sent to the provided callback URL.";

  public static final String ERROR_MESSAGE =
      "Could not process request due to internal error. Please try again in some time.";

  public static final String DRIVER_ID_INVALID_MESSAGE =
      "Driver ID passed is invalid, please retry with a valid Driver Token.";

  public static final String CALLBACK_URL_INVALID_MESSAGE =
      "Callback URL passed is invalid, please retry with a valid URL.";

  public static final String STATION_ID_INVALID_MESSAGE =
      "Station ID passed is invalid, please retry with a valid UUID for Station ID.";

  public static final String VALID_REQUEST = "All fields are valid";

}
