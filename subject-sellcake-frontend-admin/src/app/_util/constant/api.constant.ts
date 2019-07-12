export const BaseUrl = {
  USER: '/user',
  AUTH: '/auth/user',
  PRODUCT_TYPE: '/product-type',
  CUSTOMER: '/customer',
  SLIDER: '/slider',
  PRODUCT: '/product',
  BILLS: '/bill'
};
export const API = {
  // login, logout user
  USER_DETAIL_LOGINED: BaseUrl.USER + '/detail',
  USER_DETAIL: BaseUrl.USER + '/detail/',
  USER_CREATE: BaseUrl.USER + '/create',
  USER_LIST: BaseUrl.USER + '/list',
  CHANGE_STATUS_USER: BaseUrl.USER + '/change-status',
  CHANGE_LANG_USER: BaseUrl.USER + '/change-language',

  LOGIN: BaseUrl.AUTH + '/login',
  LOGOUT: BaseUrl.AUTH + '/logout',
  // product type
  PRODUCT_TYPE_LIST: BaseUrl.PRODUCT_TYPE + '/list',
  PRODUCT_TYPE_CREATE: BaseUrl.PRODUCT_TYPE + '/create',
  CHANGE_PRODUCT_TYPE_STATUS: BaseUrl.PRODUCT_TYPE + '/change-status',
  PRODUCT_TYPE_DETAIL: BaseUrl.PRODUCT_TYPE + '/detail/',
  PRODUCT_TYPE_UPDATE: BaseUrl.PRODUCT_TYPE + '/update',
  // customer
  CUSTOMER_LIST: BaseUrl.CUSTOMER + '/list',
  GET_CUSTOMER: BaseUrl.CUSTOMER + '/detail/',
  CHANGE_CUSTOMER_STATUS: BaseUrl.CUSTOMER + '/change-status',
  // slider
  SLIDER_CREATE: BaseUrl.SLIDER + '/create',
  SLIDER_LIST: BaseUrl.SLIDER + '/list',
  SLIDER_CHANGE_STATUS: BaseUrl.SLIDER + '/change-status',
  // product
  PRODUCT_DETAIL: BaseUrl.PRODUCT + '/detail/',
  CHANGE_PRODUCT_STATUS: BaseUrl.PRODUCT + '/change-status',
  PRODUCT_UPDATE: BaseUrl.PRODUCT + '/update',
  PRODUCT_CREATE: BaseUrl.PRODUCT + '/create',
  PRODUCT_LIST: BaseUrl.PRODUCT + '/list',
  // bills
  BILL_LIST: BaseUrl.BILLS + '/list',
  BILL_DETAIL: BaseUrl.BILLS + '/detail',
  CHANGE_BILL_STATUS: BaseUrl.BILLS + '/change-status',
  BILL_EXPORT_TO_EXCEL: BaseUrl.BILLS + '/export-to-excel',
  BILL_LIST_BY_PAYMENT: BaseUrl.BILLS + '/payment',
};
