package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public String StartDate(River value) {
		final String sql = "SELECT MIN(f.day) AS primaData "
				+ "FROM flow f  "
				+ "WHERE f.river=? "
				+ "GROUP BY f.river";

		LocalDate d = null;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,value.getId());
			ResultSet res = st.executeQuery();

			if (res.next()) {
				 d= res.getDate("primaData").toLocalDate();
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return d.toString();
	}

	public String EndDate(River value) {
		
			final String sql = "SELECT MAX(f.day) AS primaData "
					+ "FROM flow f  "
					+ "WHERE f.river=? "
					+ "GROUP BY f.river";

			LocalDate d = null;

			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1,value.getId());
				ResultSet res = st.executeQuery();

				if (res.next()) {
					 d= res.getDate("primaData").toLocalDate();
				}

				conn.close();
				
			} catch (SQLException e) {
				//e.printStackTrace();
				throw new RuntimeException("SQL Error");
			}
			return d.toString();
		}

	public String NumMis(River value) {
		final String sql = "SELECT COUNT(*) AS num "
				+ "FROM flow f  "
				+ "WHERE f.river=? "
				+ "GROUP BY f.river";

		int count= 0;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,value.getId());
			ResultSet res = st.executeQuery();

			if (res.next()) {
				 count= res.getInt("num");
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return ""+count;
	}

	public String FMed(River value) {
		final String sql = "SELECT  AVG(f.flow) AS avgflow "
				+ "FROM flow f  "
				+ "WHERE f.river=? "
				+ "GROUP BY f.river";

		Double avgflow=0.0;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,value.getId());
			ResultSet res = st.executeQuery();

			if (res.next()) {
				 avgflow= res.getDouble("avgflow");
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return ""+avgflow;
	}
	
}
