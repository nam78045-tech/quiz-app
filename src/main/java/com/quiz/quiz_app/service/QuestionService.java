package com.quiz.quiz_app.service;

import com.quiz.quiz_app.model.Question;
import com.quiz.quiz_app.model.Subject;
import com.quiz.quiz_app.repository.QuestionRepository;
import com.quiz.quiz_app.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepo;
    private final SubjectRepository subjectRepo;

    public QuestionService(QuestionRepository questionRepo, SubjectRepository subjectRepo) {
        this.questionRepo = questionRepo;
        this.subjectRepo = subjectRepo;
    }

    public List<Question> getBySubject(Long subjectId) {
        return questionRepo.findBySubjectId(subjectId);
    }

    private Question parseAndFill(Question q, String rawText, String correctAnswer) {
        String[] options = rawText.split("(?i)(?=[ABCD]\\))");
        q.setQuestionText(options[0].trim());
        q.setOptionA(options.length > 1 ? options[1].replaceFirst("(?i)A\\)\\s*", "").trim() : "");
        q.setOptionB(options.length > 2 ? options[2].replaceFirst("(?i)B\\)\\s*", "").trim() : "");
        q.setOptionC(options.length > 3 ? options[3].replaceFirst("(?i)C\\)\\s*", "").trim() : "");
        q.setOptionD(options.length > 4 ? options[4].replaceFirst("(?i)D\\)\\s*", "").trim() : "");
        q.setCorrectAnswer(correctAnswer.toUpperCase());
        return q;
    }

    public Question save(Long subjectId, String rawText, String correctAnswer) {
        Subject subject = subjectRepo.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found"));
        Question q = new Question();
        q.setSubject(subject);
        return questionRepo.save(parseAndFill(q, rawText, correctAnswer));
    }

    public Question update(Long id, String rawText, String correctAnswer) {
        Question q = questionRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        return questionRepo.save(parseAndFill(q, rawText, correctAnswer));
    }

    public void delete(Long id) {
        questionRepo.deleteById(id);
    }
} 