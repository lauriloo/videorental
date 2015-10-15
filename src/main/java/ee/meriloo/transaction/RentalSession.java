package ee.meriloo.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lauri on 14.10.2015.
 */
public class RentalSession {

    private List<Transaction> transactions;
    private Date begin;
    private Date end;
    private boolean finished;

    public RentalSession(){
        this.transactions = new ArrayList<Transaction>();
        this.begin = new Date();
        this.finished = false;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isFinished() {
        return finished;
    }

    public void finish() {
        this.setEnd(new Date());
        this.finished = true;
    }

    public RentalSession addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        return this;
    }
}
