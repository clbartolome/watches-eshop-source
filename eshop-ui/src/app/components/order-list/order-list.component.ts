import { Component, OnInit } from '@angular/core';
import { ApiService } from './../../service/api.service';
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  Order: any = [];
  constructor(private apiService: ApiService,
    private router: Router) {
    this.readOrder();
  }
  ngOnInit() { }
  readOrder() {
    this.apiService.getOrders().subscribe((data) => {
      this.Order = data;
    })
  }

  onUpdate(id: string | null) {
    this.apiService.updateOrderStatus(id).subscribe(data => {
      console.log("Updated to :" + data['shippingStatus']);
      this.readOrder()
    });
  }

}
