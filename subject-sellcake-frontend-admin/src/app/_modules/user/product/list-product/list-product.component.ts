import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ProductService} from '../../../service/product.service';
import {Product} from '../../../model/product';
import {Router} from '@angular/router';
import {CustomToastrService} from '../../../service/custom-toastr.service';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ListProductComponent implements OnInit {

  // paging search sort
  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };
  products: Array<Product> = [];
  collectionSize: number;

  constructor(
    private productService: ProductService,
    private router: Router,
    private  toastrService: CustomToastrService,
    private translate: TranslateService
    ) {}

  ngOnInit() {
    this.getListProduct();
  }

  // get list product
  getListProduct() {
    this.productService.getListProduct(this.pagingRequest)
      .subscribe(response => {
          this.products = response.content;
          this.collectionSize = response.totalElements;
      });
  }
  changeProductStatus(id) {
  this.productService.changeStatusProduct(id).subscribe(res => {
    // tslint:disable-next-line:triple-equals
    if (res.status == 200) {
      this.getListProduct();
      this.router.navigateByUrl('admin/product/list');
    }
  });
  }
}
