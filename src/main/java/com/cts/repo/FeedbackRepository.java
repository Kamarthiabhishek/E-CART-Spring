package com.cts.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Feedbacks;

public interface FeedbackRepository extends JpaRepository<Feedbacks, Integer>{

}
