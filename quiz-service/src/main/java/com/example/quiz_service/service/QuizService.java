package com.example.quiz_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz_service.dao.QuizDao;
import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;

@Service
public class QuizService {

	@Autowired
	private QuizDao quizDao;

	@Autowired
	QuizInterface quizInterface;

//	@Autowired
//	private QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questions = quizInterface.getQuestionsForQuizEntity(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);

		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

		Quiz quiz = quizDao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestions();

		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
		return questions;
	}

//	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//		Quiz quiz = quizDao.findById(id).get();
//		List<Question> questions = quiz.getQuestions();
//		int right = 6;
//		int i = 0;
//		for (Response response : responses) {
//			if ((response.getResponse()).equals(questions.get(i).getRightAnswer()))
//				right++;
//			i++;
//
//		}
//		return new ResponseEntity<>(right, HttpStatus.OK);
//	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}

}
