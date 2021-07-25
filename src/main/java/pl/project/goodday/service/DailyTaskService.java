package pl.project.goodday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.project.goodday.dto.DailyTaskDto;
import pl.project.goodday.dto.MyUserDetails;
import pl.project.goodday.dto.UserDto;
import pl.project.goodday.model.DailyTask;
import pl.project.goodday.model.User;
import pl.project.goodday.repository.DailyTaskRepository;
import pl.project.goodday.repository.UserRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public DailyTaskService(DailyTaskRepository dailyTaskRepository, UserRepository userRepository, MyUserDetailsService myUserDetailsService) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.userRepository = userRepository;
        this.myUserDetailsService = myUserDetailsService;
    }

    public List<DailyTask> findAllTasksForUser() {
        User loggedUserFromDb = myUserDetailsService.getLoggedUserFromDb();
        return findTasksForLoggedUser(loggedUserFromDb);
    }

    public void saveDailyTask(DailyTaskDto dailyTaskDto) {
        User loggedUserFromDb = myUserDetailsService.getLoggedUserFromDb();
        List<DailyTask> tasksForLoggedUser = findTasksForLoggedUser(loggedUserFromDb);
        DailyTask dailyTask = new DailyTask();
        dailyTask.setTask(dailyTaskDto.getTask());
        dailyTask.setUser(loggedUserFromDb);
        dailyTask.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        dailyTask.setSuccess(dailyTaskDto.getSuccess());
        tasksForLoggedUser.add(dailyTask);
        dailyTaskRepository.save(dailyTask);
    }

    private List<DailyTask> findTasksForLoggedUser(User loggedUserFromDb) {
        return dailyTaskRepository.findByUser(loggedUserFromDb);

    }
}

