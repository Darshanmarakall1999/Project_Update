package Adminpack;

import ConnectionPack.ConnectionToDataBase;
import TrainPack.Train;

import java.sql.*;
import java.util.ArrayList;

public class Adminmoduleimpl implements Adminmodules {
    private static Connection con = null;

    @Override
    public String AddATrain(Train train) {
        PreparedStatement ps = null;
        con = ConnectionToDataBase.getConnection();
        String str = "insert into trains values(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(str);
            ps.setInt(1, train.getTrainNo());
            ps.setString(2, train.getTrainName());
            ps.setString(3, train.getSource());
            ps.setString(4, train.getDest());
            ps.setDouble(5, train.getTicketPrice());
            ps.executeUpdate();
            return "One row inserted: Train inserted successfully......";
        } catch (SQLException e) {
            //e.printStackTrace();
            return ("insertion failed due to exception......");
        }
    }

    @Override
    public String DeleteATrain(int train_number) {
        con = ConnectionToDataBase.getConnection();
        PreparedStatement ps = null;
        String str = "delete from trains where train_no=?";
        try {
            ps = con.prepareStatement(str);
            ps.setInt(1, train_number);
            ps.executeUpdate();
            return "one row deleted: Train number " + train_number + " deleted successfully.....";
        } catch (SQLException e) {
            //e.printStackTrace();
            return "Entered train number not found...";
        }
    }

    @Override
    public ArrayList<Train> getAllTrains() {
        con = ConnectionToDataBase.getConnection();
        ArrayList<Train> blist = new ArrayList<Train>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from TRAINS");
            while (rs.next()) {
                Train train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
                blist.add(train);
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("train not found");
        }
        return blist;
    }

    @Override
    public Train GetATrain(int train_number) {
        Statement st=null;
        Train train=null;
        con = ConnectionToDataBase.getConnection();
        try {
            st=con.createStatement();
            ResultSet rs= st.executeQuery("select * from trains where train_no= "+train_number);
            while(rs.next())
            {
               train=new Train(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return train;
    }
}
