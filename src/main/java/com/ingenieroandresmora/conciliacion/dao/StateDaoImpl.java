package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ingenieroandresmora.conciliacion.model.State;
import com.ingenieroandresmora.conciliacion.dao.StateDao;

@Repository
@Transactional
public class StateDaoImpl extends AbstractSession implements StateDao {

	@Override
	public void saveState(State state) {
		// TODO Auto-generated method stub
		getSession().persist(state);
	}

	@Override
	public void deleteStateById(Long idState) {
		// TODO Auto-generated method stub
		State state = findById(idState);
		if (state  != null) {
			getSession().delete(state);
		}
	}

	@Override
	public void updateState(State state) {
		// TODO Auto-generated method stub
		getSession().update(state);
	}

	@Override
	public List<State> findAllStates() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from State").list();
	}

	@Override
	public State findById(Long idState) {
		// TODO Auto-generated method stub
		return (State) getSession().get(State.class, idState);
	}

	@Override
	public List<State> findByCategory(Long idCategory) {
		// TODO Auto-generated method stub
		return  getSession().createQuery(
				"from State where stateCategory = :idCategory")
				.setParameter("idCategory", idCategory).list();
	}
	
	@Override
	public State findByName(String name) {
		// TODO Auto-generated method stub
		return (State) getSession().createQuery(
				"from State where stateName = :name")
				.setParameter("name", name).uniqueResult();
	}

}
