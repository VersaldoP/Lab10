package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao ;
	List<River> rivers;
	
	public Model() {
		rivers = new ArrayList<>();
		dao =new RiversDAO();
		
	}
	
	public List<River> Rivers() {
		rivers = dao.getAllRivers();
		return rivers;
	}

	public String StartDate(River value) {
		// TODO Auto-generated method stub
		return dao.StartDate(value);
	}

	public String EndDate(River value) {
		// TODO Auto-generated method stub
		return dao.EndDate(value);
	}

	public String NumMis(River value) {
		// TODO Auto-generated method stub
		return dao.NumMis(value);
	}

	public String FMed(River value) {
		// TODO Auto-generated method stub
		return dao.FMed(value);
	}
	
}
