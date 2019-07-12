export const BaseUrl = {
    AUTH: '/auth',
    SLIDER: '/slider',
    PRODUCT: '/product',
    PRODUCT_TYPE: '/product-type',
    CUSTOMER: '/customer',
    CART: '/cart',
    BILL: '/bill'
};
export const API = {
  // login
    CUSTOMER_LOGIN: BaseUrl.AUTH + '/customer/login',
    CUSTOMER_LOGOUT: BaseUrl.AUTH + '/logout',
  // slider
    SLIDER_LIST: BaseUrl.SLIDER + "/client/list",
  // product
    PRODUCT_LISTS: BaseUrl.PRODUCT + "/client/list",
    PRODUCT_DETAIL: BaseUrl.PRODUCT + "/client/detail/",
    PRODUCT_BY_PRODUCTTYPEID:BaseUrl.PRODUCT + "/detail/productId/",
  // product type
    PRODUCT_TYPE_LIST: BaseUrl.PRODUCT_TYPE + "/client/list",
  // customer
    CUSTOMER_CREATE: BaseUrl.CUSTOMER + "/create",
    DETAIL_CUSTOMER_BY_TOKEN: BaseUrl.CUSTOMER + "/detail",
  // cart
    CART_ADD: BaseUrl.CART + "/add",
    CART_LIST: BaseUrl.CART + "/list/cart",
    CART_DELETE_ONE: BaseUrl.CART + "/delete/",
    CART_DELETE_ALL: BaseUrl.CART + "/delete/all/",
    CART_DELETE_ALL_BY_CUSTOMERID: BaseUrl.CART + "/delete/all/customer",
  // bill
    BILL_CREATE: BaseUrl.BILL + "/create",
    BILL_DETAIL_BY_CUSTOMER: BaseUrl.BILL + "/detail/customer"
};
