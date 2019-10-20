package com.maliblo.fincam;

public class Constants {
    public static final String LOG_TAG = "FinCam";

    public static final String NANONETS_MODEL_ID ="******************************";
    public static final String NANONETS_APP_ID="******************************8";

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_ID_VALUE = "d0360ea9-390f-4706-bb56-1756168bff1b";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CLIENT_SECRET_VALUE = "O4aN1wD2oF5wP1mB4jN5oO4vF7vA8iG4gG2bG6lF8fH8bJ6xP7";
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_VALUE = "client_credentials";
    public static final String GRANT_TYPE_VALUE2 = "authorization_code";

    public static final String SCOPE = "scope";
    public static final String SCOPE_VALUE = "TPPOAuth2Security";
    public static final String SCOPE_VALUE2 = "UserOAuth2Security";
    public static final String CODE = "code";
    public static final int DATA_CODE_INTENT1 = 2156;

    public static final String CONTENT_TYPE = "client_id=d0360ea9-390f-4706-bb56-1756168bff1b&client_secret=tN1vP4qR6nJ0bH6nK7iY8cJ0hR2jQ3sT5xI0qW6lP5cW7kE4lC&scope=TPPOAuth2Security";
    public static final String ACCEPT = "accept: application/json";

    public static final String DB_NAME = "appDB";
    public static final int DATA_CODE_INTENT_DETAILS = 200022;


    public static final int CAMERA_RESULT = 0;
    public static final int PICK_PHOTO_RESULT = 1;

    public static final String LBL_BARCODE = "barcode";
    public static final String LBL_COMPANY_NAME = "company_name";
    public static final String LBL_INVOICE_CHECK_DIGITS = "invoice_check_digits";
    public static final String LBL_INVOICE_NO = "invoice_no";
    public static final String LBL_TOTAL = "total";

    public static final String EAC = "Electricity Authority of Cyprus";
    public static final String EPIC = "EPIC";
    public static final String CYTA = "CYTA";

    //Used in verification after data extraction
    public static final int EAC_BARCODE_MIN_LENGTH = 20;
    public static final String EAC_BARCODE_REQUESTED_CHAR = ",";
    public static final int EAC_INVOICE_CHECK_DIGITS_LENGTH = 3;
    public static final int EAC_INVOICE_NO_LENGTH=11;
    public static final String EAC_TOTAL_REQUESTED_CHAR = "€";

    public static final int MOP_BARCODE_LENGTH = 13;
    public static final int MOP_INVOICE_NO_LENGTH=15;
    public static final int LOG_IN=1001;

    public static final int SPLASH_SECONDS = 3000;
    public static final String TOTAL_AMOUNT = "3001";

}
