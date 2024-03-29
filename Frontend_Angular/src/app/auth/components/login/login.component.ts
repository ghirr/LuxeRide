import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  isSpining: boolean=false;
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder){}
  ngOnInit(){
    this.loginForm=this.fb.group({
      email:[null,[Validators.required,Validators.email]],
      password:[null,[Validators.required]],
    })
  }

  login(){
    console.log(this.loginForm.value);
    
  }

}
