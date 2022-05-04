package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDao {
	
	public User getUserById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from users where user_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new User(				
					rs.getInt("user_id"),
					rs.getString("username"),
					rs.getString("pass"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString("email"),
					rs.getInt("role_id_fk")
					);
			}
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get userby id");
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from users where username = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new User(				
					rs.getInt("user_id"),
					rs.getString("username"),
					rs.getString("pass"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString("email"),
					rs.getInt("role_id_fk")
					);
			}
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get user by username");
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByEmail(String email) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from users where email = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new User(				
					rs.getInt("user_id"),
					rs.getString("username"),
					rs.getString("pass"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString("email"),
					rs.getInt("role_id_fk")
					);
			}
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get user by email");
			e.printStackTrace();
		}
		return null;
	}
	
	public void createUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "insert into users "
			+"(username, pass, firstname, lastname, email, role_id_fk)"
					+ "values (?,?,?,?,?,?);";
					
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//ps.setInt(1, user.getId());
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword()); 
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRoleID());

			ps.executeUpdate();

			System.out.println("created new user: " + user.getUsername());
				
			} catch (SQLException e) {
				System.out.println("Something went wrong creating new user");
				e.printStackTrace();
			}
	}
	
}
