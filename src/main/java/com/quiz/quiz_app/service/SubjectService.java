package com.quiz.quiz_app.service;

import com.quiz.quiz_app.model.Subject;
import com.quiz.quiz_app.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository repo;

    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    public List<Subject> getAll() { return repo.findAll(); }
    public Subject save(Subject s) { return repo.save(s); }
}