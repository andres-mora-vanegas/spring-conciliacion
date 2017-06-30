package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Employ;

public interface EmployService {

	void saveEmploy(Employ employ);

	void deleteEmployById(Long idEmploy);

	void updateEmploy(Employ employ);

	void updatePass(Long idEmploy,String pass);
	
	void updateWithoutPass(Employ employ);
	
	void resetPass(Long idEmploy,String oldPass);

	Employ findById(Long idEmploy);

	Employ findByEmail(String email);
	
	Employ findByIdentification(String identification);
	
	List<Employ> findAllEmploys();
	
	List<Employ> findActiveConciliator();
	
	List<Employ> findActive();
}
