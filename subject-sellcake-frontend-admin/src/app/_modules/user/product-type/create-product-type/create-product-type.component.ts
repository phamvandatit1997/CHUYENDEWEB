import { Component, OnInit } from '@angular/core';
import {ProductTypeService} from '../../../service/product-type.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomToastrService} from '../../../service/custom-toastr.service';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-create',
  templateUrl: './create-product-type.component.html',
  styleUrls: ['./create-product-type.component.css']
})
export class CreateProductTypeComponent implements OnInit {
  productTypeInfo: any = {
    productTypeName: '',
    productTypeDescription: ''
  };
  imgUrl: string;
  constructor(
    private productTypeService: ProductTypeService,
    private router: Router,
    private route: ActivatedRoute,
    private toastrService: CustomToastrService,
    private translate: TranslateService
  ) {
    this.route.queryParams.subscribe(params => {
        this.productTypeInfo.productTypeName = params['productTypeName'];
        this.productTypeInfo.productTypeDescription = params['productTypeDescription'];
        this.productTypeInfo.image = params['image'];
        this.imgUrl = params['image'] || '';
    });
  }
  ngOnInit() {}
  // create-product-type product type
  createProductType() {
    this.productTypeService.createProductType(this.productTypeInfo)
      .subscribe(response => {
        if (response.status === 200) {
          this.toastrService.showSuccessToastr('product_type.create_successfuly');
          this.router.navigate(['/admin/product-type/list']);
        } else {
          this.toastrService.showErrorAPI(response.status);
        }
      });
  }

  // read url images
  readUrl(event: any) {
    if (event.target.files && event.target.files.item(0)) {
      this.productTypeInfo.imgFile = event.target.files.item(0);
      const reader = new FileReader();
      // tslint:disable-next-line:no-shadowed-variable
      reader.onload = (event: ProgressEvent) => {
        // @ts-ignore
        this.imgUrl = (<FileReader>event.target).result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }
}
