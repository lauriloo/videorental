package ee.meriloo.transaction;

import ee.meriloo.clients.Customer;
import ee.meriloo.items.RentableItem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lauri on 12.10.2015.
 */
public class Transaction {

    private Customer customer;
    private RentableItem rentableItem;
    private TransactionType transactionType;
    private boolean greatSuccess;
    private Date date;
    public static List<Transaction> transactions = new LinkedList<Transaction>();

    public Transaction(Customer customer, RentableItem rentableItem, TransactionType transactionType, boolean greatSuccess) {
        this.customer = customer;
        this.rentableItem = rentableItem;
        this.transactionType = transactionType;
        this.greatSuccess = greatSuccess;
        this.date = new Date();
        transactions.add(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public RentableItem getRentableItem() {
        return rentableItem;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public boolean isGreatSuccess() {
        return greatSuccess;
    }

    public Date getDate() {
        return date;
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }
}
