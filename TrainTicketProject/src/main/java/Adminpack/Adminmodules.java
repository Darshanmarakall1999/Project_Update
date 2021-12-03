package Adminpack;

import TrainPack.Train;

import java.util.ArrayList;

public interface Adminmodules {
        String AddATrain(Train train);
        String DeleteATrain( int train_number);
        ArrayList<Train> getAllTrains();
        Train GetATrain(int train_number);

}
