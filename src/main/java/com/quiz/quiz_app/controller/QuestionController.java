package com.quiz.quiz_app.controller;

import com.quiz.quiz_app.model.Question;
import com.quiz.quiz_app.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {
    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/{subjectId}")
    public List<Question> getBySubject(@PathVariable Long subjectId) {
        return service.getBySubject(subjectId);
    }

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody Map<String, String> body) {
        Long subjectId = Long.parseLong(body.get("subjectId"));
        String rawText = body.get("rawText");
        String correctAnswer = body.get("correctAnswer");
        return ResponseEntity.ok(service.save(subjectId, rawText, correctAnswer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String rawText = body.get("rawText");
        String correctAnswer = body.get("correctAnswer");
        return ResponseEntity.ok(service.update(id, rawText, correctAnswer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}