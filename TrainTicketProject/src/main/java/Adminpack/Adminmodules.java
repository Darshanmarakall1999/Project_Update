package Adminpack;

import TrainPack.Train;

import java.util.ArrayList;

public interface Adminmodules {
        String AddATrain(Train train);
        String DeleteATrain( int train_number);
        ArrayList<Train> getAllTrains();
        Train GetATrain(int train_number);
        String UpdateATrainPrice(int train_number, double price);
        String UpdateASource(int train_number, String source);
        String UpdateADest(int train_number, String dest );
        String UpdateAName(int train_number, String name);
        String Validation(String adminid, String password);
        validation details();

}
