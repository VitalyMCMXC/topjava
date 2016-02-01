package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vmoskalik on 01.02.2016.
 */

@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Query("SELECT um FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
    UserMeal get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC")
    List<UserMeal> getAll(@Param("userId") int userId);

    @Query("SELECT um FROM UserMeal um WHERE um.user.id=:userId AND um.dateTime BETWEEN :startDate AND :endDate ORDER BY um.dateTime DESC")
    List<UserMeal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    UserMeal save (UserMeal meal);
}
