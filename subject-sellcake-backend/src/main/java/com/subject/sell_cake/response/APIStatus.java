package com.subject.sell_cake.response;

public enum APIStatus {
    OK(200, "OK"),
    NO_RESULT(201, "No more result."),

    //////////////////
    // CLIENT SIDE  //
    //////////////////
    ERR_BAD_REQUEST(400, "Bad request"),
    ERR_UNAUTHORIZED(401, "Unauthorized or Access Token is expired"),
    ERR_FORBIDDEN(403, "Forbidden! Access denied"),
    ERR_BAD_PARAMS(406, "Bad parameters"),
    ERR_ALREADY_EXISTED(407, "Already exsited."),
    ERR_EMAIL_ALREADY_EXISTED(408, "Email Already exsited."),

    //////////////////
    // SERVER SIDE  //
    //////////////////
    ERR_INTERNAL_SERVER(500, "Internal Server Error"),
    ERR_CREATE_MODEL(501, "Create model error"),

    //////////////////
    // SESSION SIDE //
    //////////////////
    ERR_TOKEN_NOT_FOUND(600, "Access token not found"),
    ERR_INVALID_TOKEN(601, "Access token is invalid"),
    ERR_TOKEN_EXPIRED(602, "Access token is expired"),
    ERR_SESSION_DATA_INVALID (603, "Invalid session data"),
    ERR_SESSION_NOT_FOUND(604, "Session not found"),
    ERR_ACCOUNT_INVALID (605, "Invalid account"),
    ERR_CREATE_USER_SESSION(606, "Create User Session fail"),
    ERR_CREATE_ADMIN_SESSION(607, "Create Admin Session fail"),
    ERR_BOX_TOKEN_NOT_FOUND(608, "Box Access token not found"),

    //////////////////
    // DATABASE SIDE//
    //////////////////
    ERR_INCORRECT_MODEL_DATA(700, "Incorrect model data"),
    ERR_USER_NOT_FOUND(701, "User not found."),
    ERR_PASSWORD_NOT_MATCH(702, "Password doesn't match"),
    ERR_EMAIL_ALREADY_EXISTS(703, "Email already exists"),
    ERR_FOLDER_NOT_FOUND(704, "Folder not found"),
    ERR_CREATE_CLOUD_USER(705, "Create Box cloud user fail"),
    ERR_CREATE_USER(706, "Create User fail"),
    ERR_CREATE_USER_PROXY(707, "Create User Proxy fail"),
    ERR_USER_PROXY_NOT_FOUND(708, "User Proxy not found"),
    ERR_EMAIL_INVALID(709, "Email is invalid"),
    ERR_SIGN_UP(710, "Sign up User Error"),
    ERR_INVALID_PARAM(711, "Param is invalid"),
    ERR_EXIST_EMAIL(712, "Email already exists"),
    ERR_EXIST_USER_NAME(713, "User Name already exists"),
    ERR_RESET_CODE(714, "User reset code Error"),
    ERR_FORGOT_PASS(715, "Forgot password Error"),
    ERR_RESET_CODE_EXPIRY(716, "Reset code expiry"),
    ERR_OLD_PASS(717, "Old password incorect"),
    ERR_PROXY_LOGIN_NOT_ALLOW (718, "User does not allow proxy login"),

    //////////////////
    //    File      //
    //////////////////
    ERR_FILE_NOT_FOUND(888, "File not found"),
    ERR_CANT_STORE(889, "Could not store file"),
    ERR_FILE_TYPE(890, "Could not determine file type"),

    //////////////////
    //     Box      //
    //////////////////
    ERR_CONNECT_BOX(900, "Can't connect box. Please try again"),
    ERR_GET_BOX_USER_INFO(901, "Can not get box user info."),
    ERR_REGISTER_WEBHOOKS(902, "Can not Register Webhooks"),
    ERR_GET_BOX_ACCESS_TOKEN (903, "Can not get authen model. Some thing wrong with box connection"),
    ERR_CREATE_VIEW_LINK(904, "Can not create view link"),
    ERR_GET_FOLDER_BOX(905, "Can not get Folder from Box"),
    ERR_PARSE_JSON(906, "Can not parse result to JSON"),
    ERR_CALL_API_BOX(907, "Can call API from BOX"),
    ERR_DOWNLOAD_FILE(908, " The file is not ready to be downloaded"),
    ERR_DELETE_WEBHOOKS(909, " Can not delete webhooks"),

    //////////////////
    //   Customer   //
    //////////////////
    ERR_CUSTOMER_NOT_FOUND(1002,"Customer not found"),

    //////////////////
    // Product Type //
    //////////////////
    ERR_PRODUCT_TYPE_NOT_FOUND(1001, "Product type not found"),
    ERR_PRODUCT_TYPE_EXITS(1003,"Product type exits"),

    //////////////////
    //    Bill      //
    //////////////////
    ERR_BILL_NOT_FOUND(1004,"Bill Not Found"),
    ERR_IMAGE_EXITS(1005,"File not exits"),
    ERR_BILL_DETAIL_NOT_FOUND(1006,"Bill detail not found"),

    //////////////////
    //   Product    //
    //////////////////
    ERR_PRODUCT_NOT_FOUND(1101,"Product not found"),

    //////////////////
    //    Slider    //
    //////////////////
    ERR_SLIDER_NOT_FOUND(1102, "Slider not found"),
    ERR_USER_EXITS(1103, "User Exits");

    private final int status;
    private final String description;
    APIStatus(int c, String d){
        status = c;
        description = d;}

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
