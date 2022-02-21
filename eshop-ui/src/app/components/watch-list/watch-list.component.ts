import { Component, OnInit } from '@angular/core';
import { ApiService } from './../../service/api.service';

@Component({
  selector: 'app-watch-list',
  templateUrl: './watch-list.component.html',
  styleUrls: ['./watch-list.component.css']
})
export class WatchListComponent implements OnInit {

  Watch: any = [];
  constructor(private apiService: ApiService) {
    this.readWatch();
  }
  ngOnInit() { }
  readWatch() {
    this.apiService.getEmployees().subscribe((data) => {
      this.Watch = data;
    })
  }


}
