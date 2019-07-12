import {Component,OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../service/product.service';
import {Product} from '../../models/product';
import {CartService} from '../../../service/cart.service';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  id: string;
  product: Product;
  products: Array<Product> = [];

  pagingRequest: any = {
    searchKey: "",
    sortCase:1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 8
  };
  listProducts: Array<Product> = [];

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private translateService: TranslateService
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.id = params['id'];
        this.productService.getProductById(this.id).subscribe(response => {
            this.product = response;
            this.productService.getProductByProductTypeId(this.product.productTypeId).subscribe(response => {
                this.products = response;
            });
        });
    });
    this.getListProducts();
  }
  // get list product paging
  getListProducts() {
    this.productService.getListProductPaging(this.pagingRequest).subscribe(response => {
        this.listProducts = response.content;
    });
  }

  // add cart
  addCart(id: string) {
      this.cartService.addCart(id);
  }
}
