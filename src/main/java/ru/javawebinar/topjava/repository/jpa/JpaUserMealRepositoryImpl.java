package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()){
            em.persist(userMeal);
            em.createNamedQuery(UserMeal.SET_userId).
                    setParameter("userid", userId).
                    setParameter("id", userMeal.getId()).
                    executeUpdate();
            return userMeal;
        } else {
            UserMeal um = get(userMeal.getId(), userId);
            em.merge(userMeal);
            em.createNamedQuery(UserMeal.SET_userId).
                    setParameter("userid", userId).
                    setParameter("id", userMeal.getId()).
                    executeUpdate();
            return userMeal;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE).
                setParameter("id", id).
                setParameter("userid", userId).
                executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return em.createNamedQuery(UserMeal.GET, UserMeal.class).
                setParameter("id", id).
                setParameter("userid", userId).
                getSingleResult();
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).
                setParameter("userid", userId).
                getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.BETWEEN, UserMeal.class).
                setParameter("startDate", startDate).
                setParameter("endDate", endDate).
                setParameter("userid", userId)
                .getResultList();
    }
}