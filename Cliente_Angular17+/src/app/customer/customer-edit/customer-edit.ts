import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CustomerService } from '../customer.service';
import { Customer } from '../model/customer';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpErrorResponse } from '@angular/common/http' //Añado esto para recibir el error del back
import { ChangeDetectorRef } from '@angular/core';

@Component({
    selector: 'app-customer-edit',
    standalone: true,
    imports: [FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule ],
    templateUrl: './customer-edit.html',
    styleUrl: './customer-edit.scss'
})
export class CustomerEditComponent implements OnInit {
    customer!: Customer;
    errorMessage: string | null = null;

    constructor(
        public dialogRef: MatDialogRef<CustomerEditComponent>,
        private customerService: CustomerService,
        @Inject(MAT_DIALOG_DATA) public data: {customer : Customer},
        private cdr: ChangeDetectorRef,
    ) {}

    ngOnInit(): void {
        this.customer = this.data.customer ? Object.assign({}, this.data.customer) : new Customer();
    }

    onSave() {
    this.errorMessage = null;
    this.customerService.saveCustomers(this.customer).subscribe({
        next: () => {
            this.dialogRef.close(true);
        },
        error: (error: HttpErrorResponse) => {
            if (error.status === 409) {
                this.errorMessage = "El cliente ya está registrado";
            } else {
                this.errorMessage = "Ocurrió un error inesperado.";
            }
            
            this.cdr.detectChanges(); 
            
            console.error(error);
         }
      });
    }
    

    onClose() {
      this.dialogRef.close();
    }
}

