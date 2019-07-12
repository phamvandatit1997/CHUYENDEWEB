import { Component, OnInit } from '@angular/core';
import {SessionUserService} from '../../../service/session-user.service';
import {CustomerService} from '../../../service/customer.service';
import {Router} from '@angular/router';
import {CartService} from '../../../service/cart.service';
import {Cart} from '../../models/cart';
import {BillService} from '../../../service/bill.service';
import {Customer} from '../../models/customer';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-layout-header',
  templateUrl: './layout-header.component.html',
  styleUrls: ['./layout-header.component.css']
})
export class LayoutHeaderComponent implements OnInit {

  customer: Customer;
  customerId: string;
  numberProduct: number = 0;
  sumPrice: number = 0;
  show = true;
  carts: Array<Cart> = [];
  carResponse: any = {
    numberProduct: this.numberProduct,
    sumPrice: this.sumPrice,
    listCart: this.carts
  };

  constructor(
    private sessionService: SessionUserService,
    private customerService: CustomerService,
    private router: Router,
    private cartService: CartService,
    private billService: BillService,
    private translateService: TranslateService
  ) {
      this.customerService.detailCustomerByToken().subscribe(response => {
        this.customer = response;
        if (this.customer == null) {
          this.customerId = '';
        } else {
          this.customerId = response.customerId;
          this.show = false;
        }
      });
  }

  ngOnInit() {
      this.cartService.getListCart().subscribe(response => {
          this.carts = response.listCart;
          this.carResponse = response;
      });
  }


// logout customer
  logoutCustomer() {
     this.customerService.logout();
     this.router.navigateByUrl('/cake/home');
     this.show = true;
  }

  // delete one product in cart
  deleteOneProductInCart(id: string) {
      this.cartService.deleteOneProductInCart(id).subscribe(response => {});
  }
  // delete all product in cart
  deleteAllProductInCart(id: string) {
      this.cartService.deleteAllProductInCart(id).subscribe(response => {});
  }
  // create bill
  createBill() {
      this.billService.createBill(this.carResponse);
  }
  // change lang
  changeLang(lang: string) {
    this.translateService.use(lang);
  }
}
