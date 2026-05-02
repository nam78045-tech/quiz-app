package com.quiz.quiz_app.repository;

import com.quiz.quiz_app.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {}