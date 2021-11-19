package at.kaindorf.springburger.repo;

import at.kaindorf.springburger.beans.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {

    @Query("select b from Burger b where b.name = ?1 order by b.id")
    List<Burger> findByNameOrderById(String name);

}