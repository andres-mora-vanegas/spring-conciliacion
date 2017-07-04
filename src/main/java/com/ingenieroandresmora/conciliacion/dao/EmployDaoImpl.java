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
		if (employ != null) {
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
		return (Employ) getSession().createQuery("from Employ where employEmail = :email").setParameter("email", email)
				.uniqueResult();
	}

	@Override
	public void updatePass(Long idEmploy, String pass) {
		String hqlUpdate = "update Employ e set e.employPass = :pass where e.employId = :id";
		int updatedEntities = getSession().createQuery(hqlUpdate).setParameter("id", idEmploy).setParameter("pass", pass)
				.executeUpdate();
	}

	@Override
	public void updateWithoutPass(Employ employ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetPass(Long idEmploy,String oldPass) {		
		String hqlUpdate = "update Employ e set e.employPass = :oldPass where e.employId = :id";
		int updatedEntities = getSession().createQuery(hqlUpdate).setParameter("id", idEmploy).setParameter("pass", oldPass)
				.executeUpdate();

	}

	@Override
	public Employ findByIdentification(String identification) {
		return (Employ) getSession().createQuery("from Employ where employIdentification = :identification").setParameter("identification",identification).uniqueResult();		
	}

	@Override
	public List<Employ> findActiveConciliator() {
		
		/*List<Object[]> objects =getSession().createQuery(
				"from Employ te join te.employId tem where te.employState =1")
				.list();
		if (objects.size() > 0) {
			for (Object[] objects2 : objects) {
				for (Object object : objects2) {
					if (object instanceof Employ) {
						return (Employ) object;
					}
				}
			}
		}
		
		return null;*/
		return getSession().createQuery("from Employ te,RolEmploy tre "
				+ "where te.employId=tre.rolEmployEmployId and tre.rolEmployRolId=3 and te.employState=1").list();
	}

	@Override
	public List<Employ> findActive() {
		return getSession().createQuery("from Employ te "
				+ "where te.employState=1").list();
	}

}
