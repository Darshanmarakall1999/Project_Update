package TicketPack;

import PessengerPack.Passenger;
import TrainPack.Train;

import java.util.Date;
import java.util.TreeMap;

public class Ticket {
    private int counter;
    private String pnr;
    private Date travelDate;
    //Train train=new Train();
    //TreeMap<Passenger,Integer> passengers=new TreeMap<Passenger, Integer>();

    public Ticket(Date travelDate, Train train) {
        this.travelDate = travelDate;
        //this.train = train;
    }
}