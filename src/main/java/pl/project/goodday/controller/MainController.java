package pl.project.goodday.controller;

import pl.project.goodday.model.DailyTask;
import pl.project.goodday.model.GoldenThought;
import pl.project.goodday.service.DailyTaskService;
import pl.project.goodday.repository.GoldenThoughtsRepository;
import pl.project.goodday.service.MyUserDetailsService;
import pl.project.goodday.service.UserAlreadyExistException;
import pl.project.goodday.dto.DailyTaskDto;
import pl.project.goodday.dto.UserDto;
import pl.project.goodday.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class MainController {

    private final MyUserDetailsService myUserDetailsService;
    private final DailyTaskService dailyTaskService;
    private final GoldenThoughtsRepository goldenThoughtsRepository;
    @Value("${hearth.beat}")
    private String hearthBeatMsg;

    @Autowired
    public MainController(MyUserDetailsService myUserDetailsService, DailyTaskService dailyTaskService, GoldenThoughtsRepository goldenThoughtsRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.dailyTaskService = dailyTaskService;
        this.goldenThoughtsRepository = goldenThoughtsRepository;
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/showDailyTask")
    public String showDailyTask(){
        return "Pozmywać okna";
    }

    @PostMapping("/register")
    public String registerUserAccount(@RequestBody UserDto userDto) {
        try {
            User registered = myUserDetailsService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException | IllegalStateException uaeEx) {
            return uaeEx.getMessage();
        }
        return "Użytkownik został zarejestrowany";
    }

    @GetMapping("/goldenThought")
    public String getRandomGoldenThought() {
        int sententencesQty = goldenThoughtsRepository.countAllGoldenThoughtsInDb();
        Random random = new Random();
        Optional<GoldenThought> goldenThoughtsOptional = goldenThoughtsRepository.findById(random.nextInt(sententencesQty) + 1);
        GoldenThought goldenThought = goldenThoughtsOptional.orElse(new GoldenThought("Iwona Coelho", "Idź do pracy, nie słuchaj kołczów"));
        return String.format("%s - %s", goldenThought.getSentence(), goldenThought.getAuthor());
    }

    @PostMapping("/addTask")
    public String addTask(@RequestBody DailyTaskDto dailyTaskDto) {
//        dailyTaskService. ..... (dailyTaskDto); - wrócić do skasowanej metody
        return "dodano task dla użytkownika";
    }

    @GetMapping("/findAllTasksForUser")
    public List<DailyTask> findAllTasksForUser() {
        return dailyTaskService.findAllTasksForUser();
    }

    @GetMapping("/ping")
    public String hearthBeat() {
        return hearthBeatMsg;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("Logged successfull", HttpStatus.OK);
    }
}

