package pl.project.goodday.service;

import pl.project.goodday.client.RestClient;
import pl.project.goodday.dto.GoldenThoughtDto;
import pl.project.goodday.dto.UserDto;
import pl.project.goodday.model.GoldenThought;
import pl.project.goodday.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.project.goodday.repository.GoldenThoughtsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class DbInitializer implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(DbInitializer.class);
    private final MyUserDetailsService myUserDetailsService;
    private RestClient restClient;
    private GoldenThoughtsRepository goldenThoughtsRepo;


    @Autowired
    public DbInitializer(MyUserDetailsService myUserDetailsService, RestClient restClient, GoldenThoughtsRepository goldenThoughtsRepo) {
        this.myUserDetailsService = myUserDetailsService;
        this.restClient=restClient;
        this.goldenThoughtsRepo = goldenThoughtsRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        createAdmin();
        loadGoldenThoughts();
    }

    private void loadGoldenThoughts() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = () -> {
            Set<GoldenThoughtDto> goldenThoughts= new HashSet<>();
            for (int i = 0; i < 5; i++) {
                goldenThoughts.add(restClient.fetchGoldenThoughts()[0]);
            }
            List<GoldenThought> goldenThoughtsEntities = goldenThoughts.stream().map(goldenThoughtDto -> new GoldenThought(goldenThoughtDto.getA(), goldenThoughtDto.getQ())).collect(Collectors.toList());
            goldenThoughtsRepo.saveAll(goldenThoughtsEntities);
            logger.info("Golden thoughts saved successfully!");
        };
        scheduler.scheduleAtFixedRate(runnable,0,30, TimeUnit.MINUTES);
    }

    private void createAdmin() {
        if (!myUserDetailsService.checkIfAdminExists()){
            UserDto admin = createUser();
            User registered = myUserDetailsService.registerNewUserAccount(admin);
            logger.info("Admin with username:{} and password {} was created",registered.getUserName(),registered.getPassword());
        }
    }

    private UserDto createUser() {
        UserDto user = new UserDto();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setMatchingPassword("admin");
        return user;
    }
}
