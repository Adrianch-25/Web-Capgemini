import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Loan } from './model/Loan';
import { LoanPage } from './model/LoanPage';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})
export class LoanService {
    constructor(private http: HttpClient) {}
    private baseUrl = 'http://localhost:8080/loan';

  
    getLoans(pageable: Pageable, gameTitle?: string, clientName?: string, date?: Date) {
    return this.http.post<LoanPage>(this.baseUrl, {
        pageable,
        gameTitle,
        clientName,
        date
    });
    }


    saveLoan(loan: Loan): Observable<Loan> {
        const { id } = loan;
        const url = id ? `${this.baseUrl}/${id}` : this.baseUrl;
        return this.http.put<Loan>(url, loan);
    }

    deleteLoan(idLoan: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${idLoan}`);
    }

    
    getClients() {
    return this.http.get<any[]>('http://localhost:8080/customer');
    }

    getGames() {
    return this.http.get<any[]>('http://localhost:8080/game');
    }

}
