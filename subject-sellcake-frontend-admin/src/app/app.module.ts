import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import {AppRoutingModel} from './app.routing';
import {AppComponent} from './app.component';
import {TranslateService} from './_modules/service/translate.service';
import {ForgetPasswordComponent} from './_modules/user/auth/forget-password/forget-password.component';
import {LoginComponent} from './_modules/user/auth/login/login.component';
import {LayoutContainerComponent} from './_layout/layout-container/layout-container.component';
import {ListProductTypeComponent} from './_modules/user/product-type/list-product-type/list-product-type.component';
import {DashboardComponent} from './_modules/user/dashboard/dashboard.component';
import {CreateProductTypeComponent} from './_modules/user/product-type/create-product-type/create-product-type.component';
import {TranslatePipe} from './_util/pipes/translate.pipe';
import {ListCustomerComponent} from './_modules/user/customer/list-customer/list-customer.component';
import {DetailCustomerComponent} from './_modules/user/customer/detail-customer/detail-customer.component';
import {EditProductTypeComponent} from './_modules/user/product-type/edit-product-type/edit-product-type.component';
import {CreateSliderComponent} from './_modules/user/slider/create-slider/create-slider.component';
import {CreateProductComponent} from './_modules/user/product/create-product/create-product.component';
import {ListProductComponent} from './_modules/user/product/list-product/list-product.component';
import {ListSliderComponent} from './_modules/user/slider/list-slider/list-slider.component';
import {ModalBoxDirective} from './_util/directive/modal-box.directive';
import {ListBillComponent} from './_modules/user/bills/list-bill/list-bill.component';
import {BillDetailComponent} from './_modules/user/bills/bill-detail/bill-detail.component';
import {CookieService} from 'ngx-cookie-service';
import {ApiService} from './_modules/service/api.service';
import {ProductTypeService} from './_modules/service/product-type.service';
import {SecurityService} from './_modules/service/security.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {HttpTokenInterceptor} from './_core/interceptors';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {ToastrModule} from 'ngx-toastr';
import {NgxEditorModule} from 'ngx-editor';
import {EditProductComponent} from './_modules/user/product/edit-product/edit-product.component';
import { UserCreateComponent } from './_modules/user/auth/user-create/user-create.component';
import { UserListComponent } from './_modules/user/auth/user-list/user-list.component';
import { UserDetailComponent } from './_modules/user/auth/user-detail/user-detail.component';

export function setupTranslateFactory(service: TranslateService): Function {
  return () => service.use('vi');
}

@NgModule({
  declarations: [
    AppComponent,
    ForgetPasswordComponent,
    LoginComponent,
    LayoutContainerComponent,
    ListProductTypeComponent,
    DashboardComponent,
    CreateProductTypeComponent,
    TranslatePipe,
    ListCustomerComponent,
    DetailCustomerComponent,
    EditProductTypeComponent,
    CreateSliderComponent,
    CreateProductComponent,
    ListProductComponent,
    ListSliderComponent,
    ModalBoxDirective,
    ListBillComponent,
    BillDetailComponent,
    EditProductComponent,
    UserCreateComponent,
    UserListComponent,
    UserDetailComponent,
  ],
  imports: [
    NgbModule,
    BrowserAnimationsModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModel,
    RouterModule.forRoot([]),
    ToastrModule.forRoot({
      timeOut: 1000,
      autoDismiss: true,
      closeButton: true,
      positionClass: 'toast-top-center',
      preventDuplicates: false,
      progressBar: true
    }),
    NgxEditorModule
  ],
  providers: [
    CookieService,
    ApiService,
    ProductTypeService,
    SecurityService,
    TranslateService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: setupTranslateFactory,
      deps: [TranslateService],
      multi: true
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
