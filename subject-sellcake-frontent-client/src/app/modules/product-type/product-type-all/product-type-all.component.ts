import { Component, OnInit } from '@angular/core';
import {Product} from '../../models/product';
import {ProductService} from '../../../service/product.service';
import {CartService} from '../../../service/cart.service';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-product-type-all',
  templateUrl: './product-type-all.component.html',
  styleUrls: ['./product-type-all.component.css']
})
export class ProductTypeAllComponent implements OnInit {

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
    private cartService: CartService,
    private translateService: TranslateService
  ) { }

  ngOnInit() {
    this.getListProduct();
  }
  // get list product
  getListProduct(){
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
