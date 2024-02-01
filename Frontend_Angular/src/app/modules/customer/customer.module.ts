import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CostumerDashboardComponent } from './components/costumer-dashboard/costumer-dashboard.component';


@NgModule({
  declarations: [
    CostumerDashboardComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule
  ]
})
export class CustomerModule { }
