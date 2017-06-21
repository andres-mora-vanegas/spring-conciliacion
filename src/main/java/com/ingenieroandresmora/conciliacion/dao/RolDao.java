package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Rol;

public interface RolDao {
	void saveRol(Rol Rol);

	void deleteRolById(Long idRol);

	void updateRol(Rol Rol);

	List<Rol> findAllRols();

	Rol findById(Long idRol);

	Rol findByName(String name);
}
