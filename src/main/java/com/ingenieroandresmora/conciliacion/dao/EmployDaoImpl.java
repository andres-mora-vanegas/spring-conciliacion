package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ingenieroandresmora.conciliacion.model.Employ;
import com.ingenieroandresmora.conciliacion.model.State;


@Repository
@Transactional
public class EmployDaoImpl extends AbstractSession implements EmployDao {

	@Override
	public void saveEmploy(Employ employ) {
		// TODO Auto-generated method stub
		getSession().persist(employ);
	}

	@Override
	public void deleteEmployById(Long idEmploy) {
		// TODO Auto-generated method stub
		Employ employ = findById(idEmploy);
		if (employ  != null) {
			getSession().delete(employ);
		}
	}

	@Override
	public void updateEmploy(Employ employ) {
		// TODO Auto-generated method stub
		getSession().update(employ);
	}

	@Override
	public List<Employ> findAllEmploys() {
		return getSession().createQuery("from Employ").list();
	}

	@Override
	public Employ findById(Long idEmploy) {
		return (Employ) getSession().get(Employ.class, idEmploy);
	}

	@Override
	public Employ findByEmail(String email) {
		return (Employ) getSession().createQuery(
				"from Employ where employEmail = :email")
				.setParameter("email", email).uniqueResult();
	}

	@Override
	public void updatePass(Long idEmploy, String pass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWithoutPass(Employ employ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetPass(Long idEmploy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employ findByIdentification(String identification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employ> findActiveConciliator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employ> findActive() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
