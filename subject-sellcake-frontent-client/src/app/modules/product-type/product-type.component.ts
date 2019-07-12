import { Component, OnInit } from '@angular/core';
import {ProductTypeService} from '../../service/product-type.service';
import {ProductType} from '../models/product-type';
import {TranslateService} from '../../service/translate.service';

@Component({
  selector: 'app-product-type',
  templateUrl: './product-type.component.html',
  styleUrls: ['./product-type.component.css']
})
export class ProductTypeComponent implements OnInit {

  productTypes: Array<ProductType> = [];

  constructor(
    private productTypeService: ProductTypeService,
    private translateService: TranslateService
  ) { }

  ngOnInit() {
    this.getListProductType();
  }
  // get list product type
  getListProductType(){
    this.productTypeService.getListProductType().subscribe(response => {
      this.productTypes = response;
    });
  }
}
