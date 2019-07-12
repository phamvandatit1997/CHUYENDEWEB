import { Component, OnInit } from '@angular/core';
import {Slider} from '../../../model/slider';
import {SliderService} from '../../../service/slider.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-list-slider',
  templateUrl: './list-slider.component.html',
  styleUrls: ['./list-slider.component.css']
})
export class ListSliderComponent implements OnInit {

  // paging search sort
  pagingRequest: any = {
    searchKey: '',
    sortCase: 1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };
  sliders: Array<Slider> = [];
  collectionSize: number;

  constructor(
    private sliderService: SliderService,
    private router: Router,
    private translate: TranslateService
  ) { }

  ngOnInit() {
    this.getListSlider();
  }
  // get list slider
  getListSlider() {
    this.sliderService.getListSlider(this.pagingRequest)
      .subscribe(response => {
        this.sliders = response.content;
        this.collectionSize = response.totalElements;
      });
  }

  // change status slider
  changeStatusSlider(id: string) {
    this.sliderService.changeStatusSlider(id).subscribe(respose => {
      // tslint:disable-next-line:triple-equals
      if (respose.status == 200) {
        this.getListSlider();
        this.router.navigateByUrl('/admin/slider/list');
      }
    });
  }
}
