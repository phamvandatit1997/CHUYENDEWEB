import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModel} from './app.routing';
import {RouterModule} from '@angular/router';
import {ApiService} from './service/api.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxHmCarouselModule} from 'ngx-hm-carousel';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { LayoutHeaderComponent } from './modules/layout/layout-header/layout-header.component';
import { LayoutSliderComponent } from './modules/layout/layout-slider/layout-slider.component';
import { LayoutFooterComponent } from './modules/layout/layout-footer/layout-footer.component';
import { LayoutCenterComponent } from './modules/layout/layout-center/layout-center.component';
import { HomeComponent } from './modules/home/home.component';
import { ProductDetailComponent } from './modules/products/product-detail/product-detail.component';
import { ProductsComponent } from './modules/products/products.component';
import { ProductTypeComponent } from './modules/product-type/product-type.component';
import { ProductTypeAllComponent } from './modules/product-type/product-type-all/product-type-all.component';
import { ProductTypeDetailComponent } from './modules/product-type/product-type-detail/product-type-detail.component';
import { CustomerComponent } from './modules/customer/customer.component';
import { CustomerLoginComponent } from './modules/customer/customer-login/customer-login.component';
import { CustomerRegisterComponent } from './modules/customer/customer-register/customer-register.component';
import {ToastrModule} from 'ngx-toastr';
import {TranslateService} from './service/translate.service';
import { TranslatePipe } from './util/pipes/translate.pipe';
import {CookieService} from 'ngx-cookie-service';
import {SecurityService} from './service/security.service';
import {HttpTokenInterceptor} from './modules/_core/interceptors';
import { CustomerDetailComponent } from './modules/customer/customer-detail/customer-detail.component';
import {SessionUserService} from './service/session-user.service';
import { BillDetailComponent } from './modules/bill-detail/bill-detail.component';

export function setupTranslateFactory(service: TranslateService): Function {
  return () => service.use('en');
}
@NgModule({
  declarations: [
    AppComponent,
    LayoutHeaderComponent,
    LayoutSliderComponent,
    LayoutFooterComponent,
    LayoutCenterComponent,
    HomeComponent,
    ProductDetailComponent,
    ProductsComponent,
    ProductTypeComponent,
    ProductTypeAllComponent,
    ProductTypeDetailComponent,
    CustomerComponent,
    CustomerLoginComponent,
    CustomerRegisterComponent,
    TranslatePipe,
    CustomerDetailComponent,
    BillDetailComponent,
  ],
  imports: [
    BrowserAnimationsModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    NgxHmCarouselModule,
    BrowserModule,
    AppRoutingModel,
    RouterModule.forRoot([]),
    NgbModule.forRoot(),
    ToastrModule.forRoot({
      timeOut: 2000,
      autoDismiss: true,
      closeButton: true,
      positionClass: 'toast-bottom-right',
      preventDuplicates: false,
      progressBar: true
    }),
  ],
  providers: [
    ApiService,
    CookieService,
    SecurityService,
    SessionUserService,
    TranslateService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },
    ,
    {
      provide: APP_INITIALIZER,
      useFactory: setupTranslateFactory,
      deps: [TranslateService],
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
