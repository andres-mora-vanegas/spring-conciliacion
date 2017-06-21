package com.ingenieroandresmora.conciliacion.dao;

import java.util.List;

import com.ingenieroandresmora.conciliacion.model.Answer;

public interface AnswerDao {
	void saveAnswer(Answer Answer);

	void deleteAnswerById(Long idAnswer);

	void updateAnswer(Answer Answer);

	List<Answer> findAllAnswers();

	Answer findById(Long idAnswer);

	Answer findByName(String name);
}
