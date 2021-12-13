package Adminpack;

import ConnectionPack.ConnectionToDataBase;
import ConnectionPack.adminconnection;
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
        Statement st = null;
        Train train = null;
        con = ConnectionToDataBase.getConnection();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from trains where train_no= " + train_number);
            while (rs.next()) {
                train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return train;
    }

    @Override
    public String UpdateATrainPrice(int train_number, double price) {
        con = ConnectionToDataBase.getConnection();
        PreparedStatement ps = null;
        String str = "UPDATE trains set ticket_price=? where train_no=?";
        try {
            ps = con.prepareStatement(str);
            ps.setInt(2, train_number);
            ps.setDouble(1, price);
            ps.executeUpdate();
            return " Train number " + train_number + " data updated successfully.......";
        } catch (SQLException e) {
            //e.printStackTrace();
            return "Entered train number " + train_number + " not found:";
        }
    }

    @Override
    public String UpdateASource(int train_number, String source) {
        con = ConnectionToDataBase.getConnection();
        PreparedStatement ps = null;
        String str = "UPDATE trains set source=? where train_no=?";
        try {
            ps = con.prepareStatement(str);
            ps.setInt(2, train_number);
            ps.setString(1, source);
            ps.executeUpdate();
            return " Train number " + train_number + " data updated successfully.......";
        } catch (SQLException e) {
            //e.printStackTrace();
            return "Entered train number " + train_number + " not found:";
        }
    }

    @Override
    public String UpdateADest(int train_number, String dest) {
        con = ConnectionToDataBase.getConnection();
        PreparedStatement ps = null;
        String str = "UPDATE trains set destination=? where train_no=?";
        try {
            ps = con.prepareStatement(str);
            ps.setString(1, dest);
            ps.setInt(2, train_number);
            ps.executeUpdate();
            return " Train number " + train_number + " data updated successfully.......";
        } catch (SQLException e) {
            //e.printStackTrace();
            return "Entered train number " + train_number + " not found:";
        }
    }

    @Override
    public String UpdateAName(int train_number, String name) {
        con = ConnectionToDataBase.getConnection();
        PreparedStatement ps = null;
        String str = "UPDATE trains set train_name=? where train_no=?";
        try {
            ps = con.prepareStatement(str);
            ps.setString(1, name);
            ps.setInt(2, train_number);
            ps.executeUpdate();
            return " Train number " + train_number + " data updated successfully.......";
        } catch (SQLException e) {
            //e.printStackTrace();
            return "Entered train number " + train_number + " not found:";
        }
    }

    @Override
    public String Validation( int adminid,String password) {
        con = adminconnection.getConnection();
        Statement st=null;
        String str=password;
        try {
            st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from admin where adminid="+adminid);
            while(rs.next())
            {
                if(str.equals(rs.getString(2)))
                    return "true";
                else
                    return "false";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "false";
        }
        return null;
    }

    @Override
    public validation details() {
            con = adminconnection.getConnection();
            validation ad = null;
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from admin");
                while (rs.next()) {
                    ad = new validation(rs.getString(1),rs.getString(2));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("train not found");
            }
            return ad;
        }
    }
