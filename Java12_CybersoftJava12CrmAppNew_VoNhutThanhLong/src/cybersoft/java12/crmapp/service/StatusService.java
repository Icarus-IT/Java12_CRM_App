package cybersoft.java12.crmapp.service;

import java.sql.SQLException;

import cybersoft.java12.crmapp.dao.StatusDao;
import cybersoft.java12.crmapp.dto.StatusDto;

public class StatusService {
	StatusDao dao;
	public StatusService() {
		dao = new StatusDao();
	}
	public StatusDto getStatusById(int id) {
		
		try {
			if  (dao.getStatusById(id)!=null) {
				
				return dao.getStatusById(id); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
