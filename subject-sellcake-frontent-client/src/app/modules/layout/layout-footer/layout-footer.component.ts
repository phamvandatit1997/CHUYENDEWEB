import { Component, OnInit } from '@angular/core';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-layout-footer',
  templateUrl: './layout-footer.component.html'
})
export class LayoutFooterComponent implements OnInit {

  constructor(
    private translateService: TranslateService
  ) { }

  ngOnInit() {
  }

}
