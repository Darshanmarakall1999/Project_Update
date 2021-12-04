package TicketPack;

import PessengerPack.Passenger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import TrainPack.TrainOpImpl;
import TrainPack.Train;
public class TicketOpImpl implements TicketOp{
    TreeMap<Passenger,Double> passengers=new TreeMap<Passenger, Double>();
    TrainOpImpl TOI=new TrainOpImpl();
    private String sr;
    private StringBuilder fText=null;


    @Override
    public String generatePNR(int train_no,String str) {
        Train train=TOI.getTrainDetails(train_no);
        String[] word=str.split("/");
        String fstr="";
        for (String s:word)
        {
            fstr+=s;
        }

        Random rand=new Random();
        int count=(rand.nextInt(100)+100);
        String s3=Integer.toString(count);
        char ch=train.getSource().charAt(0);
        String s1=Character.toString(ch);
        char ch1=train.getDest().charAt(0);
        String s2=Character.toString(ch1);
        String s=s1+s2+"_"+fstr+"_"+s3;
        sr=s;
        return (s);
    }

    @Override
    public Double calcPassengerFare(int train_no,int age,char gender) {
        Train train=TOI.getTrainDetails(train_no);
        double price=train.getTicketPrice();
        if(age<=12)
        {
            price=price*0.5;
            return price;
        }
        else
        {
            if (age>=60)
            {
                price=price*0.6;
                return price;
            }
            else
            {
                if(gender=='F')
                {
                    price=price*0.75;
                    return price;
                }
                else
                {
                    return price;
                }
            }
        }
    }


    @Override
    public void addPassenger(String name, int age, char gender) {

    }

    @Override
    public double calculateTotalTicketPrice(Passenger p,double price) {
        //TreeMap<Passenger,Double> passengers=new TreeMap<Passenger, Double>();
        passengers.put(p,price);
        double value=0;
        for(Map.Entry<Passenger,Double> entry:passengers.entrySet())
        {
            value+=entry.getValue();
            //Passenger key=entry.getKey();

        }
        return value;
    }
    @Override
    public StringBuilder generateTicket(int train_no,String str,double price,Passenger p) {
        Train train=TOI.getTrainDetails(train_no);
        StringBuilder sb=new StringBuilder();
        sb.append("PNR\t\t  : "+generatePNR(train_no,str)+"\r\nTrain no\t  : "
                +train.getTrainNo()+"\r\nTrain Name        : "+train.getTrainName()+"\r\nFrom\t\t  : "+train.getSource()+"\r\nTo\t\t  : "
                +train.getDest()+"\r\nTravel Date       : "+str+"\r\n\r\nPassengers : \r\nName\t\tAge\t\tGender\t\tFare");
        for(Map.Entry<Passenger,Double> entry:passengers.entrySet())
        {
            //String key=entry.getKey()
            sb.append(entry.getKey());
            sb.append(entry.getValue());

        }
        sb.append("\r\n\r\nTotal Price  : "+calculateTotalTicketPrice(p,price));


        fText=sb;
        return sb;
    }

    @Override
    public void writeTicket() {
        System.out.println("Your ticket has been booked successfully with pnr no.:"+sr);

        File file=new File("C:\\Users\\Darshan\\Desktop\\BootCampers\\TrainProject\\"+sr+".txt");
        try
                (
                        FileOutputStream output=new FileOutputStream(file);
                        BufferedOutputStream bs=new BufferedOutputStream(output);
                ) {
            bs.write(fText.toString().getBytes());
            System.out.println("you can download your ticket from desktop. Enjoy your Travelling");

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}