package com.subject.sell_cake.util;

public interface Constant {
    public static final String SYSTEM_ADMIN_ID = "th1s1ssyst3m4dm1n0d3l3t3th1sus3r";
    public static final int SALT_LENGTH = 6;
    public static final String HEADER_TOKEN = "X-Access-Token";

    ///////////////////
    ////// api ////////
    ///////////////////
    public static final String API = "api/v1";
    public static final String WITHIN_ID = "/{id}";

    ///////////////////
    ////// url use ////
    ///////////////////
    public static final String USER = API + "/user";
    public static final String USER_DETAIL_LOGINED = "/detail";
    public static final String USER_DETAIL = "/detail/{id}";
    public static final String USER_CREATE = "/create";
    public static final String USER_LIST = "/list";
    public static final String USER_CHANGE_STATUS = "/change-status";
    public static final String USER_CHANGE_LANGUAGE = "/change-language";
    ///////////////////
    ////// slider /////
    ///////////////////
    public static final String SLIDER = API + "/slider";
    public static final String SLIDER_CREATE = "/create";
    public static final String SLIDER_UPDATE = "/update";
    public static final String SLIDER_GET_LIST = "/list";
    public static final String SLIDER_CHANGE_STATUS = "/change-status";

    ///////////////////
    // product types //
    ///////////////////
    public static final String PRODUCT_TYPES = API + "/product-type";
    public static final String PRODUCT_TYPES_CREATE = "/create";
    public static final String PRODUCT_TYPES_UPDATE = "/update";
    public static final String TYPE_PRODUCT_LIST = "/list";
    public static final String TYPE_PRODUCT_CHANGE_STATUS = "/change-status";
    public static final String TYPE_PRODUCT_FIND_ONE = "/detail/{id}";

    //////////////////////////
    // product types client //
    /////////////////////////
    public static final String TYPE_PRODUCT_LIST_CLIENT = "/client/list";

    ///////////////////
    ////// product/////
    ///////////////////
    public static final String PRODUCT = API + "/product";
    public static final String PRODUCT_CREATE = "/create";
    public static final String PRODUCT_LIST = "/list";
    public static final String PRODUCT_CHANGE_STATUS = "/change-status";
    public static final String PRODUCT_UPDATE = "/update";
    public static final String PRODUCT_FIND_ONE = "/detail/{id}";
    public static final String PRODUCT_BY_PRODUCTTYPEID = "/detail/productId/{id}";

    ////////////////////////////
    ////// product client /////
    ///////////////////////////
    public static final String PRODUCT_CLIENT_LIST = "/client/list";
    public static final String PRODUCT_CLIENT_DETAIL = "/client/detail/{id}";

    ///////////////////
    ////// slider /////
    ///////////////////
    public static final String SLIDER_CLIENT_LIST = "/client/list";
    ///////////////////
    ////// Customer ///
    ///////////////////
    public static final String CUSTOMER = API + "/customer";
    public static final String CUSTOMER_CREATE = "/create";
    public static final String CUSTOMER_GET_LIST = "/list";
    public static final String GET_ONE_CUSTOMER = "/detail/{id}";
    public static final String DETAIL_CUSTOMER = "/detail";
    public static final String CHANGE_STATUS = "/change-status";

    ///////////////////
    ////// bills //////
    ///////////////////
    public static final String BILLS = API + "/bill";
    public static final String BILL_CREATE = "/create";
    public static final String BILL_DETAIL = "/detail";
    public static final String BILL_DETAIL_CUSTOMERID = "/detail/customer";
    public static final String BILL_LIST = "/list";
    public static final String BILL_CHANGE_STATUS = "/change-status";
    public static final String BILL_DELETE = "/delete/{id}";
    public static final String BILL_EXPORT_TO_EXCEL = "/export-to-excel";
    public static final String BILL_LIST_BY_PAYMENT = "/payment";

    ///////////////////
    ////// cart ///////
    ///////////////////
    public static final String CART = API + "/cart";
    public static final String CART_ADD = "/add";
    public static final String CART_GET = "/list/cart";
    public static final String CART_DELETE_ONE = "/delete/{id}";
    public static final String CART_DELETE_ALL = "/delete/all/{id}";
    public static final String CART_DELETE_ALL_BY_CUSTOMER = "/delete/all/customer";

    ///////////////////////
    // url login, logout //
    ///////////////////////
    public static final String LOGIN_USER_API = "/auth/user/login";
    public static final String LOGIN_CUSTOMER_API = "/auth/customer/login";
    public static final String LOGOUT_API = "/auth/logout";

    ///////////////////
    ////// file ///////
    ///////////////////
    public static final String FILE = "/files";

    ////////////////////////////
    // sort case product type //
    ////////////////////////////
    public static final int PRODUCTTYPESORTBYNAME = 1;
    public static final int PRODUCTTYPESORTBYDESCRIPTION = 2;

    //////////////////////////
    // sort search bills //
    //////////////////////////
    public static final int DATEORDER = 2;
    public static final int DATESHIP = 3;
    public static final int TOTAL = 4;
    public static final int PAYMENT = 5;
    public static final int NOTE = 6;

    //////////////////////////
    // sort search customer //
    //////////////////////////
    public static final int CUSTOMERNAME = 1;
    public static final int GENDER = 2;
    public static final int BIRTHDAY = 3;
    public static final int CUSTOMEREMAIL = 4;
    public static final int CUSTOMERADDRESS = 5;
    public static final int CUSTOMERPHONENUMBER = 6;
    public static final int CUSTOMERSTATUS = 7;
    public static final int CUSTOMERCREATEDATE = 8;
    public static final int CUSTOMERLASTACTIVITY = 9;
     public enum ParamError{
        ;
        private final String code;
        private final String desc;
        ParamError(String code,String desc)
        {
            this.code = code;
            this.desc = desc;
        }

         public String getCode() {
             return code;
         }

         public String getDesc() {
             return desc;
         }
     }

     // status object
     public enum Status{
         ACTIVE(1),
         DELETE(0)
         ;
         private final int values;

        Status(int values)
        {
            this.values = values;
        }
         public int getValues() {
             return values;
         }
     }
     enum Lang{
         VI("vi"),
         EN("en");
         private final String values;

         Lang(String values)
         {
             this.values = values;
         }

         public String getValues() {
             return values;
         }
     }

     // user role
    enum UserRole{
        ADMIN("ADMIN","Admin role"),
        USER("USER", "User role")
         ;
        private final String values;
        private final String desc;
        UserRole(String values,String desc)
        {
            this.values = values;
            this.desc = desc;
        }

         public String getValues() {
             return values;
         }

         public String getDesc() {
             return desc;
         }
     }
}
