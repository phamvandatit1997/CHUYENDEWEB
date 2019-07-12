import { Component, OnInit } from '@angular/core';
import {ProductType} from '../../models/product-type';
import {ProductService} from '../../../service/product.service';
import {ActivatedRoute} from '@angular/router';
import {CartService} from '../../../service/cart.service';

@Component({
  selector: 'app-product-type-detail',
  templateUrl: './product-type-detail.component.html',
  styleUrls: ['./product-type-detail.component.css']
})
export class ProductTypeDetailComponent implements OnInit {

  id: string;
  products: Array<ProductType> = [];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private cartService: CartService
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      return this.productService.getProductByProductTypeId(this.id).subscribe(response => {
          this.products = response;
      });
    });
  }

  // add cart
  addCart(id: string) {
    this.cartService.addCart(id);
  }
}
