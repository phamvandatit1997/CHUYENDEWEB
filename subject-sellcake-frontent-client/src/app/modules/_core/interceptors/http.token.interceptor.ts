import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import {SessionUserService} from '../../../service/session-user.service';


@Injectable()
export class HttpTokenInterceptor implements HttpInterceptor {
  constructor(private session: SessionUserService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let contentType;
    let headersConfig;

    if (req.headers.has('Content-Type')) {
      contentType = req.headers.get('Content-Type');
    }

     headersConfig = {
      'Accept': 'application/json'
    };

    const token = this.session.getAccessToken();

    if (token) {
      headersConfig['X-Access-Token'] = `${token}`;
    }

    const request = req.clone({ setHeaders: headersConfig });
    return next.handle(request);
  }
}
