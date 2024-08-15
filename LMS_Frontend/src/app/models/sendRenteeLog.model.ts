export interface sendRenteeLog {
    userId : number;
    bookId : number;
    issueDate : Date;
    dueDate : Date;
    totalAmount : number;
    amountPaid : number;
}