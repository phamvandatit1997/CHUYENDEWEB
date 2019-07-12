import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutContainerComponent} from './_layout/layout-container/layout-container.component';
import {LoginComponent} from './_modules/user/auth/login/login.component';
import {DashboardComponent} from './_modules/user/dashboard/dashboard.component';
import {ListProductTypeComponent} from './_modules/user/product-type/list-product-type/list-product-type.component';
import {ListCustomerComponent} from './_modules/user/customer/list-customer/list-customer.component';
import {ListProductComponent} from './_modules/user/product/list-product/list-product.component';
import {ListSliderComponent} from './_modules/user/slider/list-slider/list-slider.component';
import {ListBillComponent} from './_modules/user/bills/list-bill/list-bill.component';
import {CreateProductTypeComponent} from './_modules/user/product-type/create-product-type/create-product-type.component';
import {EditProductTypeComponent} from './_modules/user/product-type/edit-product-type/edit-product-type.component';
import {CreateProductComponent} from './_modules/user/product/create-product/create-product.component';
import {EditProductComponent} from './_modules/user/product/edit-product/edit-product.component';
import {CreateSliderComponent} from './_modules/user/slider/create-slider/create-slider.component';
import {DetailCustomerComponent} from './_modules/user/customer/detail-customer/detail-customer.component';
import {UserCreateComponent} from './_modules/user/auth/user-create/user-create.component';
import {UserListComponent} from './_modules/user/auth/user-list/user-list.component';
import {UserDetailComponent} from './_modules/user/auth/user-detail/user-detail.component';
import {BillDetailComponent} from './_modules/user/bills/bill-detail/bill-detail.component';

const routes: Routes = [
  // router product type
  {
    path: 'admin',
    component: LayoutContainerComponent,
    children: [
      {
        path: 'dasboard',
        component: DashboardComponent
      },
      {
        path: 'product-type/list',
        component: ListProductTypeComponent
      },
      {
        path: 'product-type/create',
        component: CreateProductTypeComponent
      },
      {
        path: 'product-type/:id',
        component: EditProductTypeComponent
      },
      {
        path: 'customer/list',
        component: ListCustomerComponent
      },
      {
        path: 'customer/:id',
        component: DetailCustomerComponent
      },
      {
        path: 'product/list',
        component: ListProductComponent
      },
      {
        path: 'product/create',
        component: CreateProductComponent
      },
      {
        path: 'product/:id',
        component: EditProductComponent
      },
      {
        path: 'slider/list',
        component: ListSliderComponent
      },
      {
        path: 'sldier/create',
        component: CreateSliderComponent
      },
      {
        path: 'user/create',
        component: UserCreateComponent
      },
      {
        path: 'user/detail/:id',
        component: UserDetailComponent
      },
      {
        path: 'user/list',
        component: UserListComponent
      },
      {
        path: 'bill/list',
        component: ListBillComponent
      },
      {
        path: 'bill/detail/:id',
        component: BillDetailComponent
      }
    ]
  },
  // login
  {
    path: 'user/login',
    component: LoginComponent
  },
  {
    path: '',
    redirectTo: '/user/login',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModel {
}
