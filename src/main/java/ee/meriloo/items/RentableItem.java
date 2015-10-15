package ee.meriloo.items;


import ee.meriloo.clients.Customer;
import ee.meriloo.enums.RentableState;

import java.util.Date;

/**
 * Created by Lauri on 10.10.2015.
 */
public class RentableItem extends Item {

    private RentableState rentableState;
    private Date rentBeginTime;
    private Date rentEndTime;
    private long rentOutTimeInDays;
    private Customer itemHolder;


    RentableItem(){
        this.rentableState = RentableState.IN_STORE;
    }




    public RentableState getRentableState() {
        return rentableState;
    }

    public void setRentableState(RentableState rentableState) {
        this.rentableState = rentableState;
    }

    public Date getRentBeginTime() {
        return rentBeginTime;
    }

    public void setRentBeginTime(Date rentBeginTime) {
        this.rentBeginTime = rentBeginTime;
    }

    public Customer getItemHolder() {
        return itemHolder;
    }

    public void setItemHolder(Customer itemHolder) {
        this.itemHolder = itemHolder;
    }

    public Date getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(Date rentEndTime) {
        this.rentEndTime = rentEndTime;
    }

    public long getRentOutTimeInDays() {
        return rentOutTimeInDays;
    }

    public void setRentOutTimeInDays(long rentOutTimeInDays) {
        this.rentOutTimeInDays = rentOutTimeInDays;
    }
}
