import { Component, OnInit } from '@angular/core';
import {ProductTypeService} from '../../../service/product-type.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {ProductTypes} from '../../../model/product-types';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit-product-type.component.html',
  styleUrls: ['./edit-product-type.component.css']
})
export class EditProductTypeComponent implements OnInit {

  id: string;
  imgUrl: string = null;
  redirect = '/admin/product-type/list';
  disabled = true;
  // @ts-ignore
  currentProductType: ProductTypes = {
    productTypeId: '',
    productTypeName: '',
    productTypeDescription: '',
  };

  constructor(
    private productTypeService: ProductTypeService,
    private route: ActivatedRoute,
    private titleService: Title,
    private router: Router,
    private translate: TranslateService,
  ) {
    this.titleService.setTitle('Product Type');
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.id = params['id'];
        this.productTypeService.getProductType(this.id)
          .subscribe(productType => {
              this.currentProductType = productType;
              this.currentProductType.productTypeId = this.id;
              this.imgUrl = productType.productTypeImage;
          });
    });
  }

  // read url images
  readUrl(event: any) {
    if (event.target.files && event.target.files.item(0)) {
      this.currentProductType.imageFile = event.target.files.item(0);
      const reader = new FileReader();
      // tslint:disable-next-line:no-shadowed-variable
      reader.onload = (event: ProgressEvent) => {
        // @ts-ignore
        this.imgUrl = (<FileReader>event.target).result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }
  // update product type
  updateProductType() {
      this.productTypeService.update(this.currentProductType)
        .subscribe(response => {
          this.currentProductType = response;
          this.router.navigateByUrl(this.redirect);
        });
  }
  // change update
  changeUpdate() {
    this.disabled = false;
  }
  // change status
  changeStatus() {
      this.productTypeService.changeStatusProductType(this.currentProductType.productTypeId)
        .subscribe(response => {
          this.router.navigate(['/admin/product-type/list']);
      });
  }
}
