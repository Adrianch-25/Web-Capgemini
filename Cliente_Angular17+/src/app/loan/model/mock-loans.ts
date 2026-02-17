import { LoanPage } from './LoanPage';

export const LOAN_DATA: LoanPage = {
    content: [
        { id: 1, nameGame: 'Test1', nameClient: 'Test', loanDate: new Date ('2026-02-11'), endDate: new Date('2026-02-11')},
        { id: 2, nameGame: 'Test1', nameClient: 'Test', loanDate: new Date ('2026-02-11'), endDate: new Date('2026-02-11')},
        { id: 3, nameGame: 'Test1', nameClient: 'Test', loanDate: new Date ('2026-02-11'), endDate: new Date('2026-02-11')},

    ],
    pageable: {
        pageSize: 5,
        pageNumber: 0,
        sort: [{ property: 'id', direction: 'ASC' }],
    },
    totalElements: 3,
};