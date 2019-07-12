import { Component, OnInit } from '@angular/core';
import {SliderService} from '../../../service/slider.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslateService} from '../../../service/translate.service';

@Component({
  selector: 'app-create-slider',
  templateUrl: './create-slider.component.html',
  styleUrls: ['./create-slider.component.css']
})
export class CreateSliderComponent implements OnInit {

  imgUrl: string;
  sliderInfo: any = {};
  constructor(
    private sliderService: SliderService,
    private route: ActivatedRoute,
    private router: Router,
    private translate: TranslateService
  ) {
    this.route.queryParams.subscribe(params => {
      this.sliderInfo.image = params['image'];
      this.imgUrl = params['image'] || '';
    });
  }

  ngOnInit() {

  }
  createSlider() {
    this.sliderService.createSlider(this.sliderInfo)
      .subscribe(response => {
        if (response.status === 200) {
          this.router.navigate(['/admin/slider/list']);
        }
      });
  }
  // read url images
  readUrl(event: any) {
    if (event.target.files && event.target.files.item(0)) {
      this.sliderInfo.imgFile = event.target.files.item(0);
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
