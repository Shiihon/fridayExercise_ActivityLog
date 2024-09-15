package org.example.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.config.HibernateConfig;
import org.example.entities.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ActivityDAO implements IDAO<Activity> {
    EntityManagerFactory emf;

    public ActivityDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Activity create(Activity activity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            WeatherInfo weatherInfo = activity.getWeatherInfo();
            CityInfo cityInfo = activity.getCityInfo();

            if (cityInfo != null) {
                TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.cityName = :city_name", CityInfo.class);
                query.setParameter("city_name", cityInfo.getCityName());

                try {
                    CityInfo found = query.getSingleResult();
                    activity.setCityInfo(found);

                } catch (NoResultException e) {
                    em.persist(cityInfo);
                    cityInfo.getMunicipalities().forEach(municipality -> {
                        municipality.setCityInfo(cityInfo);
                        em.persist(municipality);
                    });
                }
            }
            if (weatherInfo.getId() == null) {
                em.persist(weatherInfo);
                em.persist(weatherInfo.getCurrentData());
            }

            em.persist(activity);
            em.getTransaction().commit();
        }
        return activity;
    }

    @Override
    public Activity update(Activity activity) {
        return null;
    }

    @Override
    public void delete(Activity activity) {

    }

    @Override
    public Activity getById(Long id) {
        return null;
    }

    @Override
    public Set<Activity> getAll() {
        return Set.of();
    }

    public static void main(String[] args) {
        ActivityDAO dao = new ActivityDAO(HibernateConfig.getEntityManagerFactory("activity_logging"));
        Set<Activity> activities = new HashSet<>();
        Set<Municipality> municipalities = new HashSet<>();

        Activity activity1 = Activity.builder()
                .comment("No comment")
                .distance(2.0)
                .duration(Duration.ofMinutes(30))
                .excersizeDate(LocalDate.now())
                .excersizeType("Riding")
                .timeOfDay(LocalTime.now())
                .build();

        WeatherInfo weather1 = WeatherInfo.builder()
                .LocationName("Roskilde")
                .build();

        CurrentData currentData = CurrentData.builder()
                .humidity(75)
                .temperature(20.0)
                .skyText("Rather cloudy today")
                .windText("Fucking windy")
                .build();

        CityInfo city1 = CityInfo.builder()
                .cityName("Roskilde")
                .cityId("2563")
                .build();

        Municipality municipality1 = Municipality.builder()
                .municipalityId("55658")
                .name("Glostrup Kommune")
                .build();

        municipalities.add(municipality1);
        city1.setMunicipalities(municipalities);

        weather1.setCurrentData(currentData);
        activity1.setCityInfo(city1);
        activity1.setWeatherInfo(weather1);

        activities.add(activity1);
        city1.setActivities(activities);

        System.out.println(dao.create(activity1));
    }

}
