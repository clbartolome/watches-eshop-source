import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { AppConfigService } from '../providers/app-config.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient,private config: AppConfigService) { }
  
  // Get all employees
  getEmployees() {
    return this.http.get(this.config.getConfig().baseUrl + this.config.getConfig().basePath);
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
