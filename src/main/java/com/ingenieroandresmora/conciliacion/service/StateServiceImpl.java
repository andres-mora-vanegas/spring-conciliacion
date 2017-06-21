package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieroandresmora.conciliacion.model.State;
import com.ingenieroandresmora.conciliacion.dao.StateDao;

@Service("stateService")
@Transactional
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDao _stateDao;
	
	@Override
	public void saveState(State state) {
		// TODO Auto-generated method stub
		_stateDao.saveState(state);
	}

	@Override
	public void deleteStateById(Long idState) {
		// TODO Auto-generated method stub
		_stateDao.deleteStateById(idState);
		
	}

	@Override
	public void updateState(State state) {
		// TODO Auto-generated method stub
		_stateDao.updateState(state);
	}

	@Override
	public List<State> findAllStates() {
		// TODO Auto-generated method stub
		return _stateDao.findAllStates();
	}

	@Override
	public State findById(Long idState) {
		// TODO Auto-generated method stub
		return _stateDao.findById(idState);
	}

	@Override
	public State findByName(String name) {
		// TODO Auto-generated method stub
		return _stateDao.findByName(name);
	}
	
	@Override
	public List<State> findByCategory(Long stateCategory) {
		// TODO Auto-generated method stub
		return _stateDao.findByCategory(stateCategory);
	}

}
