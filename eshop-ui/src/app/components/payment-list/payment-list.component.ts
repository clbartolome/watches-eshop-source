import { Component, OnInit } from '@angular/core';
import { ApiService } from './../../service/api.service';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {

  Payment: any = [];
  constructor(private apiService: ApiService) {
    this.readPayment();
  }
  ngOnInit() { }
  readPayment() {
    this.apiService.getPayments().subscribe((data) => {
      this.Payment = data;
    })
  }

}
