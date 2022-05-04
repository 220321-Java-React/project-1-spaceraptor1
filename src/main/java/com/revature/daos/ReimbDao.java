package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.dtos.ResolveDTO;
import com.revature.models.Reimb;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbDao {
	public Reimb getReimbById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from reimb where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new Reimb(				
					rs.getInt("reimb_id"),
					rs.getInt("amount"),
					rs.getInt("author"),
					rs.getInt("resolver"),
					rs.getInt("status_fk"),
					rs.getInt("type_fk")
					);
			}
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get reimb");
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reimb> getReimbsOwned(int u_id){
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from reimb where author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimb> rList = new ArrayList<>();
			while(rs.next()) {
				Reimb r = new Reimb(				
					rs.getInt("reimb_id"),
					rs.getInt("amount"),
					rs.getInt("author"),
					rs.getInt("resolver"),
					rs.getInt("status_fk"),
					rs.getInt("type_fk")
					);
				rList.add(r);
			}
			return rList;
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get reimbs");
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reimb> getReimbsNotOwned(int u_id){
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from reimb where author != ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimb> rList = new ArrayList<>();
			while(rs.next()) {
				Reimb r = new Reimb(				
					rs.getInt("reimb_id"),
					rs.getInt("amount"),
					rs.getInt("author"),
					rs.getInt("resolver"),
					rs.getInt("status_fk"),
					rs.getInt("type_fk")
					);
				rList.add(r);
			}
			return rList;
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get reimbs");
			e.printStackTrace();
		}
		return null;
	}

	public void updateReimbStatus(ResolveDTO r) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update reimb set status_fk = ?, resolver = ? where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getStatus());
			ps.setInt(2, r.getResolver());
			ps.setInt(3, r.getId());
			ps.executeUpdate();
			System.out.println("Finance manager, "+r.getResolver()
			+" Updated status to "+r.getStatus()+" for reimb "+r.getId());
					
		} catch (SQLException e) {
			System.out.println("Error updating status");
			e.printStackTrace();
		}
	}
	
	public void createReimb(Reimb r) {
try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "insert into reimb "
			+"(amount, author, status_fk, type_fk)"
					+ "values (?,?,?,?);";
					
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//ps.setInt(1, user.getId());
			ps.setInt(1, r.getAmount());
			ps.setInt(2, r.getAuthor());
			ps.setInt(3, 1);
			ps.setInt(4, r.getType());

			ps.executeUpdate();

			System.out.println("created new request ");
				
			} catch (SQLException e) {
				System.out.println("Something went wrong creating new request");
				e.printStackTrace();
			}
	}
}
