import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


const BASE_Url="http://localhost:8081/api/auth"
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  

  constructor(private http:HttpClient) { }

  register(signupRequest:any):Observable<any>{
    return this.http.post(BASE_Url+"/signup",signupRequest);
  }
}
