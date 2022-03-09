import { Payment } from './../../model/payment';
import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ApiService } from './../../service/api.service';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";


@Component({
  selector: 'app-payment-create',
  templateUrl: './payment-create.component.html',
  styleUrls: ['./payment-create.component.css']
})
export class PaymentCreateComponent implements OnInit {

  submitted = false;
  paymentForm: FormGroup;
  // paymentData: Payment[] | undefined;
  constructor(
    public fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private apiService: ApiService,
    private router: Router
  ) { }

  ngOnInit() {

    let id = this.actRoute.snapshot.paramMap.get('id');
    this.getWatch(id);
    this.paymentForm = this.fb.group({
      name: ['', [Validators.required]],
      description: [{ value: '', disabled: true }, [Validators.required]],
      card: ['', [Validators.required]],
      price: [{ value: '', disabled: true }, [Validators.required]]
    })
  }

  getWatch(id: string | null) {
    this.apiService.getWatch(id).subscribe(data => {

      this.paymentForm.setValue({
        description: data['brand']['name'] + " " + data['model'],
        price: data['price'],
        name: null,
        card: null
      });
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.paymentForm.value.name != null && this.paymentForm.value.card != null) {
      if (window.confirm('Are you sure?')) {
        this.apiService.createPayment(this.paymentForm.getRawValue()).subscribe(
          (res) => {
            console.log('Payment successfully created!')
            const body = { detail: `${res.name} - ${res.description} (${res.price} €) - ${res.createdAt}` };
            this.apiService.createOrder(body).subscribe(
              (res) => {
                console.log('Order successfully created!')
              }, (error) => {
                console.log(error);
                window.alert(error);
              });
            this.router.navigateByUrl('/payment-list');
          }, (error) => {
            console.log(error);
            window.alert(error);
          });
      }
      return true;
    } else {
      window.alert("Card details cannot be empty")
      return true;
    }
  }
  onCancel() {
    this.router.navigateByUrl('/watches-list');
  }


  // Getter to access form control
  get myForm() {
    return this.paymentForm.controls;
  }

}
