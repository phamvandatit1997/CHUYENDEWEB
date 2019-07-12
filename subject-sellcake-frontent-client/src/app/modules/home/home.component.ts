import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../models/product';
import {SessionUserService} from '../../service/session-user.service';
import {CartService} from '../../service/cart.service';
import {TranslateService} from '../../service/translate.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Array<Product> = [];
  collectionSize: number;

  pagingRequest: any = {
    searchKey: "",
    sortCase:1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 30
  };

  constructor(
    private productService: ProductService,
    private sessionService: SessionUserService,
    private cartService: CartService,
    private translateService: TranslateService
  ) { }

  ngOnInit() {
    this.getlistProduct();
  }
  getlistProduct(){
    this.productService.getListProductPaging(this.pagingRequest).subscribe(response => {
      this.products = response.content;
      this.collectionSize = response.totalElements;
    });
  }

  // add cart
  addCart(id: string) {
    this.cartService.addCart(id);
  }
}
