package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.RolEmploy;

public interface RolEmployDao {
	void saveRolEmploy(RolEmploy RolEmploy);

	void deleteRolEmployById(Long idRolEmploy);

	void updateRolEmploy(RolEmploy RolEmploy);

	List<RolEmploy> findAllRolEmploys();

	RolEmploy findById(Long idRolEmploy);

	RolEmploy findByName(String name);
}
