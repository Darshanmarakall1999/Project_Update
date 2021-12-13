package TrainPack;

public class Train {
    private int trainNo;
    private String trainName;
    private String source;
    private String dest;
    private double ticketPrice;

    public Train(int trainNo, String trainName, String source, String dest, double ticketPrice) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.dest = dest;
        this.ticketPrice = ticketPrice;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return       "trainNo = " + trainNo + "\t"+
                     ", trainName='" + trainName + "\t" +
                     ", source='" + source + "\t" +
                     ", dest='" + dest + "\t" +
                     ", ticketPrice=" + ticketPrice;
    }
}