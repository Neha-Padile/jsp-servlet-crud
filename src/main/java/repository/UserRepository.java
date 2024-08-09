package repository;

import connectionUtility.ConnectionUtility;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRepository {
    private static String SAVE_QUERY="INSERT INTO user (name,address,pincode) values(?,?,?)";
    private static String SELECT_QUERY="SELECT * FROM user";
    private static String SELECT_BY_ID="SELECT id,name,address,pincode FROM user where id=?";
    private static String DELETE_USER="DELETE FROM user where id=?";
    private static String UPDATE_USER="UPDATE user SET name=?,address=?,pincode=? WHERE id=?";

    //SAVE USER
    public void saveUser(User user)  {
        System.out.println(SAVE_QUERY);
        try(Connection connection=ConnectionUtility.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SAVE_QUERY)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getAddress());
            preparedStatement.setInt(3,user.getPincode());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //GET USER
    public List<User> getUser(){
        List<User> userList=new ArrayList<User>();
        System.out.println(SELECT_QUERY);
        try(Connection connection=ConnectionUtility.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(SELECT_QUERY)){
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String address=rs.getString("address");
                int pincode=rs.getInt("pincode");
                User user=new User(id,name,address,pincode);
                userList.add(user);
                
            }
            //connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;

    }

    //select  USER by ID
    public User selectUserById(int id){
        System.out.println(SELECT_BY_ID);
        User user=null;
        try(Connection connection=ConnectionUtility.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
            int id1=resultSet.getInt("id");
            String name= resultSet.getString("name");
            String address=resultSet.getString("address");
            int pincode=resultSet.getInt("pincode");
            user=new User(id,name,address,pincode);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    //DELETE USer
    public void deleteUser(int id){
        System.out.println(DELETE_USER);
        try(Connection connection=ConnectionUtility.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //update User
    public void updateUser(User user){
        System.out.println(UPDATE_USER);
        try(Connection connection=ConnectionUtility.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER)){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setInt(3,user.getPincode());
            preparedStatement.setInt(4,user.getId());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
