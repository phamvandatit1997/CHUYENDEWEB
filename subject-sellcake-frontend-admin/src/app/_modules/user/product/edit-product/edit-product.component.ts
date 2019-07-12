import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslateService} from '../../../service/translate.service';
import {ProductTypes} from '../../../model/product-types';
import {ProductTypeService} from '../../../service/product-type.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  id: string;
  imgUrl: string = null;
  redirect = '/admin/product/list';
  disable = true;
  // @ts-ignore
  currentProduct: any = {
    productId: '',
    productName: '',
    promotionPrice: 0,
    description: '',
    unitPrice: 0,
    productTypeId: '',
    unit: '',
  };
  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };
  productTypes: Array<ProductTypes> = [];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private translate: TranslateService,
    private productTypeService: ProductTypeService
  ) {
    this.route.params.subscribe(params => {
      this.currentProduct.image = params['image'];
      this.imgUrl = params['image'] || '';
    });
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.productService.getProduct(this.id)
        .subscribe(product => {
          this.currentProduct = product;
          this.currentProduct.productId = this.id;
          this.imgUrl = product.images;
          this.getListProductType();
        });
    });
  }

  // load file image
  readUrl(event: any) {
    if (event.target.files && event.target.files.item(0)) {
      this.currentProduct.imgFile = event.target.files.item(0);
      const reader = new FileReader();
      reader.onload = (event: ProgressEvent) => {
        // @ts-ignore
        this.imgUrl = (<FileReader>event.target).result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  // get list product type
  getListProductType() {
    this.productTypeService.getListProductType(this.pagingRequest)
      .subscribe(res => {
        this.productTypes = res.content;
      });
  }

  // update product
  updateProduct() {
    this.productService.updateProduct(this.currentProduct)
      .subscribe(res => {
        this.currentProduct = res;
        this.router.navigateByUrl(this.redirect);
      });
  }

  // enable form
  changeUpdate() {
    this.disable = false;
  }


  // change status
  changeStatus() {
    this.productService.changeStatusProduct(this.currentProduct.productId)
      .subscribe(res => {
        this.router.navigateByUrl(this.redirect);
      });
  }

}
