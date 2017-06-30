package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieroandresmora.conciliacion.dao.EmployDao;
import com.ingenieroandresmora.conciliacion.model.Employ;

@Service("employService")
@Transactional
public class EmployServiceImpl implements EmployService {

	@Autowired
	private EmployDao _employDao;
	
	@Override
	public void saveEmploy(Employ employ) {
		// TODO Auto-generated method stub
		_employDao.saveEmploy(employ);
	}

	@Override
	public void deleteEmployById(Long idEmploy) {
		_employDao.deleteEmployById(idEmploy);
	}

	@Override
	public void updateEmploy(Employ employ) {
		_employDao.updateEmploy(employ);
	}

	@Override
	public void updatePass(Long idEmploy, String pass) {
		_employDao.updatePass(idEmploy, pass);
		
	}

	@Override
	public void updateWithoutPass(Employ employ) {
		_employDao.updateWithoutPass(employ);
	}

	@Override
	public void resetPass(Long idEmploy, String oldPass) {
		_employDao.resetPass(idEmploy, oldPass);		
	}

	@Override
	public Employ findById(Long idEmploy) {
		return _employDao.findById(idEmploy);
	}

	@Override
	public Employ findByEmail(String email) {
		return _employDao.findByEmail(email);
	}

	@Override
	public Employ findByIdentification(String identification) {
		return _employDao.findByIdentification(identification);
	}

	@Override
	public List<Employ> findAllEmploys() {
		return _employDao.findAllEmploys();
	}

	@Override
	public List<Employ> findActiveConciliator() {
		return _employDao.findActiveConciliator();
	}

	@Override
	public List<Employ> findActive() {
		return _employDao.findActive();
	}

}
