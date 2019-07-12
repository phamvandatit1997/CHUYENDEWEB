import {Component, OnInit} from '@angular/core';
import {ProductTypeService} from '../../../service/product-type.service';
import {ProductTypes} from '../../../model/product-types';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomToastrService} from '../../../service/custom-toastr.service';
import {TranslateService} from '../../../service/translate.service';
import {ConfirmationDialogService} from '../../confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-list',
  templateUrl: './list-product-type.component.html',
  styleUrls: ['./list-product-type.component.css'],
})
export class ListProductTypeComponent implements OnInit {
  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };

  productTypes: Array<ProductTypes> = [];
  collectionSize: number;
  productType: ProductTypes = null;

  constructor(
    private productTypeService: ProductTypeService,
    private route: ActivatedRoute,
    private router: Router,
    private toastrService: CustomToastrService,
    private translate: TranslateService,
    private confirmationDialogService: ConfirmationDialogService
  ) {
  }

  ngOnInit() {
    this.getListProductType();
  }
  // get list-product-type product type
  getListProductType() {
    this.productTypeService.getListProductType(this.pagingRequest).subscribe(response => {
        this.productTypes = response.content;
        this.collectionSize = response.totalElements;
    });
  }
  // change status
  changeStatusProductType(id) {
    this.productTypeService.changeStatusProductType(id)
      .subscribe(response => {
        if (response.status === 200) {
          this.getListProductType();
          this.router.navigateByUrl('/admin/product-type/list');
        }
      });
  }
}
