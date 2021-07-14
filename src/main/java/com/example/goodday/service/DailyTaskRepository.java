package com.example.goodday.service;

import com.example.goodday.models.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DailyTaskRepository extends JpaRepository <DailyTask, Integer> {
    Optional<DailyTask> findByDate(Date date);
    Optional<DailyTask> findAll (String task);
    Optional<DailyTask> findAllTasks(String task);
    Optional<DailyTask> findAllSuccesses(String success);

    // wątpliwość nr 1: Czy to jest ok, czy powinnam zrobić coś w stylu "findByUserId" ??
    //Wątpliwość nr 2: A jak zrobić, żeby znalazł wszystkie taski i sukcesy danego usera?
    // Optional<DailyTask> findAll(String task && String success);
}


