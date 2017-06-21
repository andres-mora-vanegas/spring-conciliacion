package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.State;

public interface StateService {
	void saveState(State state);

	void deleteStateById(Long idState);

	void updateState(State state);

	List<State> findAllStates();

	State findById(Long idState);

	State findByName(String name);
	
	List<State> findByCategory(Long stateCategory);
}
