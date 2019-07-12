import { Injectable } from '@angular/core';
import {ReplaySubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Title} from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  data: any = {};
  private langSubject = new ReplaySubject<string>(1);
  public lang = this.langSubject.asObservable();
  constructor(
      private http: HttpClient,
      private titleService: Title
  ) { }

  use(lang: string): Promise<{}> {
    return new Promise<{}>((resolve, reject) => {
      const langPath = `assets/i18n/${lang || 'en'}.json`;
      this.langSubject.next(lang);
      this.http.get<{}>(langPath).subscribe(
        translation => {
          this.data = Object.assign({}, translation || {});
          this.titleService.setTitle(this.data['title']);
          resolve(this.data);
        },
        error => {
          this.data = {};
          resolve(this.data);
        }
      );
    });
  }
  translateLanguage(key) {
    return key.split('.').reduce((prev, current) => {
      return prev[current];
    }, this.data);
  }
}
