import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import { PaymentCreateComponent } from './components/payment-create/payment-create.component';
import { PaymentListComponent } from './components/payment-list/payment-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'watches-list' },
  { path: 'watches-list', component: WatchListComponent },
  { path: 'payment-order/:id', component: PaymentCreateComponent },
  { path: 'payment-list', component: PaymentListComponent },
  { path: 'order-list', component: OrderListComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

