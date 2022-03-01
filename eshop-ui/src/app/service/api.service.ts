import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AppConfigService } from '../providers/app-config.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient, private config: AppConfigService) { }

  // Get all watches
  getWatches() {
    return this.http.get(`${this.config.getConfig().watchUrl}${this.config.getConfig().watchPath}`);
  }

  // Get watch by id
  getWatch(id: any): Observable<any> {
    let url = `${this.config.getConfig().watchUrl}${this.config.getConfig().watchPath}/${id}`;
    return this.http.get(url, { headers: this.headers }).pipe(
      map((res: any) => {
        return res || {}
      }),
      catchError(this.errorMgmt)
    )
  }

  // Get all payments
  getPayments() {
    return this.http.get(`${this.config.getConfig().paymentUrl}${this.config.getConfig().paymentPath}`);
  }

  createPayment(data: any): Observable<any> {
    let url = `${this.config.getConfig().paymentUrl}${this.config.getConfig().paymentPath}`;
    return this.http.post(url, data)
      .pipe(
        catchError(this.errorMgmt)
      )
  }

  // Get all payments
  getOrders() {
    return this.http.get(`${this.config.getConfig().orderUrl}${this.config.getConfig().orderPath}`);
  }

  createOrder(data: any): Observable<any> {
    let url = `${this.config.getConfig().orderUrl}${this.config.getConfig().orderPath}`;
    return this.http.post(url, data)
      .pipe(
        catchError(this.errorMgmt)
      )
  }

  // Get all payments
  updateOrderStatus(id: any): Observable<any> {
    return this.http.put(`${this.config.getConfig().orderUrl}${this.config.getConfig().orderPath}/next/${id}`, null)
      .pipe(
        catchError(this.errorMgmt)
      );
  }

  // Error handling 
  errorMgmt(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
