package TrainPack;

import ConnectionPack.ConnectionToDataBase;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainOpImpl implements TrainOp{
    private static Connection con=null;
    @Override
    public Train getTrainDetails(int trainNo) {

        con= ConnectionToDataBase.getConnection();
        Train train=null;
        try
        {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from TRAINS where TRAIN_NO="+trainNo);
            while(rs.next())
            {
                train=new Train(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("train not found");
        }
        return train;
    }

}