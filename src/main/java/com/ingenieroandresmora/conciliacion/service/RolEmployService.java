package com.ingenieroandresmora.conciliacion.service;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.RolEmploy;

public interface RolEmployService {
	void saveRolEmploy(RolEmploy RolEmploy);

	void deleteRolEmployById(Long idRolEmploy);

	void updateRolEmploy(RolEmploy RolEmploy);

	List<RolEmploy> findAllRolEmploys();

	RolEmploy findById(Long idRolEmploy);

	RolEmploy findByName(String name);
}
