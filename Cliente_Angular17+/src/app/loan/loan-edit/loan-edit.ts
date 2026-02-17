import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoanService } from '../loan.service';
import { Loan } from '../model/Loan';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { provideNativeDateAdapter } from '@angular/material/core';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http' //Añado esto para recibir el error del back
import { ChangeDetectorRef } from '@angular/core';

@Component({
    selector: 'app-loan-edit',
    standalone: true,
    imports: [FormsModule, MatSelectModule,CommonModule,FormsModule,MatNativeDateModule,MatDatepickerModule, ReactiveFormsModule,MatPaginatorModule, MatFormFieldModule, MatInputModule, MatButtonModule ],
    providers: [provideNativeDateAdapter()],
    templateUrl: './loan-edit.html',
    styleUrl: './loan-edit.scss',
})
export class LoanEditComponent implements OnInit {
    errorMessage: string | null = null;
    loan!: Loan;
    date! : String;
    date2! : String;
    clienteElegido! : String;
    constructor(
        public dialogRef: MatDialogRef<LoanEditComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private loanService: LoanService,
        private cdr: ChangeDetectorRef,
    ) {}

    
    clients: any[] = [];
    games: any[] = [];

    ngOnInit(): void {
        this.loan = this.data.loan ? Object.assign({}, this.data.loan) : new Loan();
        this.loanService.getClients().subscribe(data => this.clients = data);
        this.loanService.getGames().subscribe(data => this.games = data);
    }

    onSave() {
    this.loanService.saveLoan(this.loan).subscribe({
        next: () => {
            this.dialogRef.close(true);
        },
        error: (error: HttpErrorResponse) => {
            if (error.status === 409) {
                // Esto mostrará "Invalid date..." o "Exceed max duration..." según lo que lance el ava (hAGO ESTO PORQUE COMPARTEN EL 409)
                this.errorMessage = error.error.message;
            } else {
                this.errorMessage = "Asegurate de rellenar todos los campos";
            }
            this.cdr.detectChanges();
        }
        });
    }

    onClose() {
        this.dialogRef.close();
    }
}
