package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.CaseAssign;

public interface CaseAssignDao {
	void saveCaseAssign(CaseAssign CaseAssign);

	void deleteCaseAssignById(Long idCaseAssign);

	void updateCaseAssign(CaseAssign CaseAssign);

	List<CaseAssign> findAllCaseAssigns();

	CaseAssign findById(Long idCaseAssign);

	CaseAssign findByName(String name);
}
