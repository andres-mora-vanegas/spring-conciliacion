package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Employ;

public interface EmployDao {

	void saveEmploy(Employ employ);

	void deleteEmployById(Long idEmploy);

	void updateEmploy(Employ employ);

	void updatePass(Long idEmploy,String pass);
	
	void updateWithoutPass(Employ employ);
	
	void resetPass(Long idEmploy);

	Employ findById(Long idEmploy);

	Employ findByEmail(String email);
	
	Employ findByIdentification(String identification);
	
	List<Employ> findAllEmploys();
	
	List<Employ> findActiveConciliator();
	
	List<Employ> findActive();
}
