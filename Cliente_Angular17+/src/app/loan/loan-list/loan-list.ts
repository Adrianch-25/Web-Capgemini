import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { LoanEditComponent } from '../loan-edit/loan-edit';
import { LoanService } from '../loan.service';
import { Loan } from '../model/Loan';
import { Pageable } from '../../core/model/page/Pageable';
import { DialogConfirmationComponent } from '../../core/dialog-confirmation/dialog-confirmation';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-loan-list',
  standalone: true,
  imports: [
    MatButtonModule,
    MatPaginatorModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatTableModule,
    CommonModule
  ],
  templateUrl: './loan-list.html',
  styleUrl: './loan-list.scss',
})
export class LoanListComponent implements OnInit {

  pageNumber: number = 0;
  pageSize: number = 5;
  totalElements: number = 0;
  dataSource = new MatTableDataSource<Loan>();
  displayedColumns: string[] = ['id', 'nameGame', 'nameClient', 'iniDate', 'endDate', 'action'];

  // ---- FILTROS ----
  games: any[] = [];
  clients: any[] = [];

  filterGame: any = null;
  filterClient: any = null;
  filterDate: Date = null;

  constructor(
    private loanService: LoanService,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadPage();

    this.loanService.getGames().subscribe(g => this.games = g);
    this.loanService.getClients().subscribe(c => this.clients = c);
  }

  loadPage(event?: PageEvent) {

    const pageable: Pageable = {
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
      sort: [
        { property: 'id', direction: 'ASC' }
      ]
    };

    if (event != null) {
      pageable.pageSize = event.pageSize;
      pageable.pageNumber = event.pageIndex;
    }

    const gameTitle  = this.filterGame   ? this.filterGame.title : null;
    const clientName = this.filterClient ? this.filterClient.name : null;
    const date       = this.filterDate;

    this.loanService.getLoans(pageable, gameTitle, clientName, date)
      .subscribe(data => {
        this.dataSource.data = data.content;
        this.pageNumber = data.pageable.pageNumber;
        this.pageSize = data.pageable.pageSize;
        this.totalElements = data.totalElements;
      });
  }

  onSearch() {
    this.loadPage();
  }

  onCleanFilter() {
    this.filterGame = null;
    this.filterClient = null;
    this.filterDate = null;
    this.loadPage();
  }

  createLoan() {
    const dialogRef = this.dialog.open(LoanEditComponent, {
      data: {},
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadPage();
    });
  }

  editLoan(loan: Loan) {
    const dialogRef = this.dialog.open(LoanEditComponent, {
      data: { loan: loan },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadPage();
    });
  }

  deleteLoan(loan: Loan) {
    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: {
        title: 'Eliminar préstamo',
        description:
          'Atención si borra el préstamo se perderán sus datos.<br>¿Desea eliminar el préstamo?',
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loanService.deleteLoan(loan.id).subscribe(() => {
          this.loadPage();
        });
      }
    });
  }
}