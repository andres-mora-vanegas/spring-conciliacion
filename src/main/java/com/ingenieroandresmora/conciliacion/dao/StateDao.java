package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.State;

public interface StateDao {
	void saveState(State state);

	void deleteStateById(Long idState);

	void updateState(State state);

	List<State> findAllStates();

	State findById(Long idState);
	
	List<State> findByCategory(Long idCategory);

	State findByName(String name);
}
