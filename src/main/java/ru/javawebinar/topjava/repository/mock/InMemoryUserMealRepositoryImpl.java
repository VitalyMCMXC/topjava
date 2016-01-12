package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserRepositoryImpl.class);

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
            userMeal.setUserId(LoggedUser.id());
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int userId, int id) throws NotFoundException {
        if (repository.get(id).getUserId() == userId){
            return !(repository.remove(id) == null); // false if not found
        } else throw LOG.getNotFoundException("Wrong userId " + userId);
    }

    @Override
    public UserMeal get(int userId, int id) throws NotFoundException {
        if (repository.get(id).getUserId() == userId){
            return repository.get(id);
        } else throw LOG.getNotFoundException("Wrong userId " + userId);
    }

    @Override
    public Collection<UserMeal> getAll(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        return repository.values().stream()
                .filter(um -> um.getUserId() == userId)
                .filter(um -> um.getDateTime().toLocalDate().compareTo(fromDate) >= 0)
                .filter(um -> um.getDateTime().toLocalDate().compareTo(toDate) <= 0)
                .filter(um -> um.getDateTime().toLocalTime().compareTo(fromTime) >= 0)
                .filter(um -> um.getDateTime().toLocalTime().compareTo(toTime) <= 0)
                .sorted((um1, um2) -> um1.getDateTime().compareTo(um2.getDateTime()))
                .collect(Collectors.toList());
    }
}
