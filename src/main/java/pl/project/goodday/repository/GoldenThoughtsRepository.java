package pl.project.goodday.repository;

import pl.project.goodday.model.GoldenThought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GoldenThoughtsRepository extends JpaRepository<GoldenThought,Integer> {
    @Query("select count(g) from GoldenThought g")
    int countAllGoldenThoughtsInDb();
}
