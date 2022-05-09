package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Launcher;
import com.revature.dtos.ResolveDTO;
import com.revature.models.Reimb;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbDao {
	Logger log = LogManager.getLogger(ReimbDao.class);
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
			String sql = "select * from reimb where author = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimb> rList = new ArrayList<>();
			while(rs.next()) {
				Reimb r = new Reimb(				
					rs.getInt("reimb_id"),
					rs.getDouble("amount"),
					rs.getInt("author"),
					rs.getInt("resolver"),
					rs.getInt("status_fk"),
					rs.getInt("type_fk")
					);
				rList.add(r);
			}
			return rList;
		}catch(SQLException e) {
			System.out.println("something whent wrong attempting to get reimbs ");
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reimb> getReimbsNotOwned(int u_id){
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select * from reimb where author != ? and status_fk = 1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimb> rList = new ArrayList<>();
			while(rs.next()) {
				Reimb r = new Reimb(				
					rs.getInt("reimb_id"),
					rs.getDouble("amount"),
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

	public boolean updateReimbStatus(ResolveDTO r) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "update reimb set status_fk = ?, resolver = ? where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getStatus());
			ps.setInt(2, r.getResolver());
			ps.setInt(3, r.getId());
			ps.executeUpdate();
			log.info("User id:  "+r.getResolver()
			+" Updated status to "+r.getStatus()+" for reimbursment id: "+r.getId());
			return true;
		} catch (SQLException e) {
			System.out.println("Error updating status");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean createReimb(Reimb r) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "insert into reimb "
			+"(amount, author, status_fk, type_fk)"
					+ "values (?,?,?,?);";
					
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//ps.setInt(1, user.getId());
			double a = Math.floor(r.getAmount()*100)/100;
			ps.setDouble(1, a);
			ps.setInt(2, r.getAuthor());
			ps.setInt(3, 1);
			ps.setInt(4, r.getType());
			
			ps.executeUpdate();

			log.info("Succesfully created new request ");
			return true;
			} catch (SQLException e) {
				System.out.println("Something went wrong creating new request");
				e.printStackTrace();
				return false;
			}
	}
}
