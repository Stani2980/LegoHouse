/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Logic.LegoHouseException;
import Logic.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Stanislav
 */
public class UserMapper {
    
    public static void createUser( User user ) throws LegoHouseException{
        try {
            Connection conn = DBConnector.getConnection();
            String SQL = "INSERT INTO User (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString(1, user.getEmail() );
            ps.setString(2, user.getPassword() );
            ps.setString(3, user.getRole() );
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LegoHouseException( ex.getMessage() );
        }
    }

    public static User login( String email, String password ) throws LegoHouseException {
        try {
            Connection conn = DBConnector.getConnection();
            String SQL = "SELECT id, role FROM User WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, email );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                String role = rs.getString( "role" );
                int id = rs.getInt("id");
                User user = new User( email, password, role );
                user.setId(id);
                return user;
            } else {
                throw new LegoHouseException( "User does not exist, please try again" );
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new LegoHouseException(ex.getMessage());
        }
    }
}
