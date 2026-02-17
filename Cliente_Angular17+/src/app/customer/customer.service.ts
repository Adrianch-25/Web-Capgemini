import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Customer } from './model/customer';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private http : HttpClient
  ) { }
    private baseUrl = 'http://localhost:8080/customer';

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.baseUrl);
  }
  saveCustomers(customer: Customer): Observable<Customer> {
    const { id } = customer;
    const url = id ? `${this.baseUrl}/${id}` : this.baseUrl;
    return this.http.put<Customer>(url, customer);  }

  deleteCustomers(idCustomer : number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${idCustomer}`);
  } 
}
