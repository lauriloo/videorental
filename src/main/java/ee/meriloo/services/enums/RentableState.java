package ee.meriloo.services.enums;

/**
 * Created by Lauri on 10.10.2015.
 */
public enum RentableState {
    RENTED_OUT("rented out"),
    IN_STORE("in store");


    private String state;

    RentableState(String state){
        this.state = state;
    }


    public String getState() {
        return state;
    }

    public boolean isRentable(){
        boolean rentable = false;
        if(this== RentableState.IN_STORE){
            rentable = true;
        }
        return rentable;
    }
}
