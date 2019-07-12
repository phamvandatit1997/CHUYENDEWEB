import {Component, OnInit} from '@angular/core';
import {ProductTypeService} from '../../../service/product-type.service';
import {ProductTypes} from '../../../model/product-types';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../service/product.service';
import {Title} from '@angular/platform-browser';
import {CustomToastrService} from '../../../service/custom-toastr.service';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  // product
  createProduct: any = {
    productName: '',
    productTypeId: '',
    description: '',
    unitPrice: 0,
    promotionPrice: 0,
    unit: ''
  };
  // url images
  imgUrl: string;
  comeback = '/admin/product/list';
  // paging sort search
  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };
  productTypes: Array<ProductTypes> = [];

  constructor(
    private productTypeSerive: ProductTypeService,
    private productService: ProductService,
    private toastrService: CustomToastrService,
    private translate: TranslateService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.queryParams.subscribe(params => {
      this.createProduct.productName = params['productName'];
      this.createProduct.description = params['description'];
      this.createProduct.unitPrice = params['unitPrice'];
      this.createProduct.promotionPrice = params['promotionPrice'];
      this.createProduct.productTypeId = params['productTypeId'];
      this.createProduct.image = params['image'];
      this.imgUrl = params['image'] || '';
    });
  }

  ngOnInit() {
    this.getListProductType();
  }

  // get list product type
  getListProductType() {
    this.productTypeSerive.getListProductType(this.pagingRequest)
      .subscribe(response => {
        this.productTypes = response.content;
      });
  }

  // read url images
  readUrl(event: any) {
    if (event.target.files && event.target.files.item(0)) {
      this.createProduct.imgFile = event.target.files.item(0);
      const reader = new FileReader();
      // tslint:disable-next-line:no-shadowed-variable
      reader.onload = (event: ProgressEvent) => {
        // @ts-ignore
        this.imgUrl = (<FileReader>event.target).result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  // create product
  createProducts() {
    this.productService.createProduct(this.createProduct)
      .subscribe(response => {
        // console.log(this.createProduct);
        if (response.status === 200) {
          this.toastrService.showSuccessToastr('product.product_create_successfuly');
          this.router.navigate([this.comeback]);
        } else {
          this.toastrService.showErrorAPI(response.status);
        }
      });
  }
}
