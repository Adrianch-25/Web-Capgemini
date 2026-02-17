import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Customer } from '../model/customer';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CustomerService } from '../../customer/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { CustomerEditComponent } from '../customer-edit/customer-edit';
import { DialogConfirmationComponent } from '../../core/dialog-confirmation/dialog-confirmation';

@Component({
    selector: 'app-customer-list',
    standalone: true,
    imports: [
        MatButtonModule,
        MatIconModule,
        MatTableModule,
        CommonModule
    ],
    templateUrl: './customer-list.html',
    styleUrl: './customer-list.scss'
})
export class CustomerListComponent implements OnInit{

    dataSource = new MatTableDataSource<Customer>();
    displayedColumns: string[] = ['id', 'name', 'action'];

    constructor(
      private customerService : CustomerService,
      public dialog : MatDialog,
    ) { }

    ngOnInit(): void {
      this.customerService.getCustomers().subscribe(customers => this.dataSource.data = customers);
    }
    createCustomer() {    
    const dialogRef = this.dialog.open(CustomerEditComponent, {
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });    
  }
  editCustomer(customer: Customer) {
    const dialogRef = this.dialog.open(CustomerEditComponent, {
      data: { customer }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }  
  deleteCustomer(customer: Customer) {    
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: { title: "Eliminar cliente", description: "Atención si elimina al cliente, se perderán sus datos permanentemente.<br> ¿Desea eliminarlo definitivamente?" }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.customerService.deleteCustomers(customer.id).subscribe(result => {
          this.ngOnInit();
        }); 
      }
    });
  }  
}
