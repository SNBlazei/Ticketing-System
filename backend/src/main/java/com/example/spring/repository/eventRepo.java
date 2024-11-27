package com.example.spring.repository;
import com.example.spring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface eventRepo extends JpaRepository<Event, Long> {



}
