package pl.project.goodday.repository;

import pl.project.goodday.model.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.goodday.model.User;

import java.util.List;

@Repository
public interface DailyTaskRepository extends JpaRepository <DailyTask, Integer> {

    List<DailyTask> findByUser(User user);
}


