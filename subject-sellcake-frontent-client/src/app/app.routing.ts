import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutCenterComponent} from './modules/layout/layout-center/layout-center.component';
import {HomeComponent} from './modules/home/home.component';
import {ProductsComponent} from './modules/products/products.component';
import {ProductDetailComponent} from './modules/products/product-detail/product-detail.component';
import {ProductTypeComponent} from './modules/product-type/product-type.component';
import {ProductTypeAllComponent} from './modules/product-type/product-type-all/product-type-all.component';
import {ProductTypeDetailComponent} from './modules/product-type/product-type-detail/product-type-detail.component';
import {CustomerComponent} from './modules/customer/customer.component';
import {CustomerLoginComponent} from './modules/customer/customer-login/customer-login.component';
import {CustomerRegisterComponent} from './modules/customer/customer-register/customer-register.component';
import {CustomerDetailComponent} from './modules/customer/customer-detail/customer-detail.component';
import {BillDetailComponent} from './modules/bill-detail/bill-detail.component';

const routes: Routes = [
  {
    path:'cake',
    component: LayoutCenterComponent,
    children: [
      {
        path:'home',
        component: HomeComponent
      },
      {
        path: 'product-detail',
        component: ProductsComponent,
        children: [
          {
            path:':id',
            component: ProductDetailComponent
          }
        ]
      },
      {
        path:'product-type',
        component: ProductTypeComponent,
        children: [
          {
            path: 'all',
            component: ProductTypeAllComponent
          },
          {
            path: ':id',
            component: ProductTypeDetailComponent
          }
        ]
      },
      {
        path: 'customer',
        component: CustomerComponent,
        children: [
          {
            path: 'login',
            component: CustomerLoginComponent
          },
          {
            path: 'register',
            component: CustomerRegisterComponent
          },
          {
            path: 'detail/:id',
            component: CustomerDetailComponent
          }
        ]
      },
      {
        path:'bill/detail',
        component: BillDetailComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: '/cake/home',
    pathMatch: 'full'
  }
 ];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModel {}
