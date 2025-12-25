package com.example.quiz_service.service;

import java.util.ArrayList;
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

//		Optional<Quiz> quiz = quizDao.findById(id);
//		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<QuestionWrapper>();
//
//		for (Question q : questionsFromDB) {
//			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
//					q.getOption3(), q.getOption4());
//			questionsForUser.add(qw);
//		}

		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);
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

//		Quiz quiz = quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
//
//		List<Question> questions = quiz.getQuestions();
//
//		// Create map: questionId -> correctAnswer
//		Map<Integer, String> correctAnswers = new HashMap<>();
//
//		for (Question q : questions) {
//			correctAnswers.put(q.getId(), q.getRightAnswer());
//		}

		int right = 0;

//		for (Response r : responses) {
//			String correctAnswer = correctAnswers.get(r.getId());
//
//			if (correctAnswer != null && correctAnswer.equalsIgnoreCase(r.getResponse())) {
//				right++;
//			}
//		}

		return ResponseEntity.ok(right);
	}

}
