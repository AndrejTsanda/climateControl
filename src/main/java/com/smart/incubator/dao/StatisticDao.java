package com.smart.incubator.dao;

import com.smart.incubator.domain.Statistic;
import com.smart.incubator.exception.DatabaseException;
import com.smart.incubator.exception.ErrorQueryException;
import com.smart.incubator.exception.NullDomainException;
import com.smart.incubator.exception.NullKeyException;
import com.smart.incubator.util.QueryManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * The class StatisticDao provides implementations of methods for working with the statistics table in the database.
 * The class uses the request manager, the requests are cached.
 *
 * @author Tsanda Andrej
 */
public class StatisticDao implements Dao<Statistic, Integer> {
    private static final Logger log = Logger.getLogger(StatisticDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QueryManager queryManager;


    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Method for storing statistics (readings from sensors) in the database.
     * @param statistic statistics (readings from sensors)
     * @throws DatabaseException
     */
    @Override
    public void save(final Statistic statistic) throws DatabaseException {
        if (statistic == null) {
            throw new NullDomainException("Statistic domain is null!");
        }

        try {
            this.jdbcTemplate.update(queryManager.getQuery("statistics.save"), new Object[]{
                    statistic.getTemperatureSensor_1(),
                    statistic.getTemperatureSensor_2(),
                    statistic.getTemperatureSensor_3(),
                    statistic.getHumiditySensor_1()
            });
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request for adding new statistic into database!");
        }
    }

    /**
     * Method for removing statistics (readings from sensors) by a unique identifier.
     * @param id unique identificator
     * @throws DatabaseException
     */
    @Override
    public void delete(final Integer id) throws DatabaseException {
        if (id == null) {
            throw new NullKeyException("Id for delete statistic from database is null!");
        }

        try {
            this.jdbcTemplate.update(queryManager.getQuery("statistics.delete.id"), id);
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request on delete statistic from database!");
        }
    }

    /**
     * Method for updating statistics (readings from sensors) in the database.
     * The method is used very rarely,
     * the implementation is presented only in order to maintain the integrity of the interface.
     *
     * @param statistic statistics (readings from sensors)
     * @throws DatabaseException
     */
    @Override
    public void update(final Statistic statistic) throws DatabaseException {
        if (statistic == null) {
            throw new NullDomainException("Statistic is null!");
        }

        try {
            this.jdbcTemplate.update(queryManager.getQuery("statistics.update.id"), new Object[]{
                    statistic.getTemperatureSensor_1(),
                    statistic.getTemperatureSensor_2(),
                    statistic.getTemperatureSensor_3(),
                    statistic.getHumiditySensor_1(),
                    statistic.getId()
            });
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request for update statistic into database!");
        }
    }

    /**
     * Method for retrieving statistics by a unique identifier. The implementation is not presented.
     * @param id unique identificator
     * @return nothing returns
     * @throws DatabaseException
     */
    @Deprecated
    @Override
    public Statistic getById(final Integer id) throws DatabaseException {
        return null;
    }

    /**
     * Method for obtaining the latest statistics (readings from sensors) from the database.
     * @return latest statistics (readings from sensors)
     * @throws DatabaseException
     */
    public Statistic getLast() throws DatabaseException {
        Statistic statistic;
        try {
            statistic = this.jdbcTemplate.queryForObject(queryManager.getQuery("statistics.get.last"), new BeanPropertyRowMapper<Statistic>(Statistic.class));
            return  statistic;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    /**
     * Method for obtaining all statistics (readings from sensors).
     * @return list containing statistics (readings from sensors)
     * @throws DatabaseException
     */
    @Override
    public List<Statistic> getAll() throws DatabaseException {
        try {
            return this.jdbcTemplate.query(queryManager.getQuery("statistics.get.all"), new BeanPropertyRowMapper<Statistic>(Statistic.class));
        } catch (Exception e) {
            log.error("Failed to execute the request for getting all statistics from database!");
            return Collections.<Statistic>emptyList();
        }
    }

    /**
     * Method for obtaining the amount of statistics (readings from sensors) in the database.
     * @return number of modes (number)
     * @throws DatabaseException
     */
    @Override
    public Integer getCount() throws DatabaseException {
        Integer countData = 0;

        try {
            countData = this.jdbcTemplate.queryForObject(queryManager.getQuery("statistics.get.count"), Integer.class);
            return countData;
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request for the number of rows in the database!");
        }
    }
}
