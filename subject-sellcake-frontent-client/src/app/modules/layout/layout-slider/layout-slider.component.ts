import { Component, OnInit } from '@angular/core';
import {Slider} from '../../models/slider';
import {SliderService} from '../../../service/slider.service';

@Component({
  selector: 'app-layout-slider',
  templateUrl: './layout-slider.component.html'
})
export class LayoutSliderComponent implements OnInit {

  sliders: Array<Slider> = [];

  pagingRequest: any = {
    searchKey: "",
    sortCase:1,
    ascSort: false,
    pageNumber: 1,
    pageSize: 10
  };

  constructor(
    private sliderService: SliderService
  ) { }

  ngOnInit() {
    this.getListSlider();
  }

  // get list slider
  getListSlider(){
    this.sliderService.showListSlider(this.pagingRequest).subscribe(response => {
        this.sliders = response.content;
    });
  }
}
