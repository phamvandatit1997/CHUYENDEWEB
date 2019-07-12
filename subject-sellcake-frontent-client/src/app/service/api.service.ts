import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {AppConfig} from '../util/constant/config.constant';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  private formatErrors(error: any) {
    return throwError(error.error);
  }

  get(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.get(`${AppConfig.API_PATH}${path}`, {headers: {'Content-Type': 'application/json'}, params})
      .pipe(catchError(this.formatErrors));
  }
  post(path: string, body: Object = {}): Observable<any> {
    return this.http.post(`${AppConfig.API_PATH}${path}`, JSON.stringify(body), {headers: {'Content-Type': 'application/json'}})
      .pipe(catchError(this.formatErrors));
  }
  put(path: string, body: Object = {}): Observable<any> {
    return this.http.put(`${AppConfig.API_PATH}${path}`, JSON.stringify(body), {headers: {'Content-Type': 'application/json'}})
      .pipe(catchError(this.formatErrors));
  }
  delete(path: string): Observable<any> {
    return this.http.put(`${AppConfig.API_PATH}${path}`, {headers: {'Content-Type': 'application/json'}})
      .pipe(catchError(this.formatErrors));
  }
  formData(path: string, formData: FormData): Observable<any> {
    return this.http.post(
      `${AppConfig.API_PATH}${path}`,
      formData
    ).pipe(catchError(this.formatErrors));
  }

  putFormData(path: string, formData: FormData): Observable<any> {
    return this.http.put(
      `${AppConfig.API_PATH}${path}`,
      formData
    ).pipe(catchError(this.formatErrors));
  }
}
