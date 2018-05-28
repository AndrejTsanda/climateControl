package com.smart.incubator.dao;

import com.smart.incubator.domain.Mode;
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
 * The Class ModeDao provides the release methods for working with the table modes in the database.
 * The class uses the request manager, the requests are cached.
 *
 * @author Tsanda Andrej
 */
public class ModeDao implements Dao<Mode, Integer> {
    private static final Logger log = Logger.getLogger(ModeDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QueryManager queryManager;


    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Method for saving a new mode in the database.
     * @param mode new mode
     * @throws DatabaseException
     */
    @Override
    public void save(final Mode mode) throws DatabaseException {
        if (mode == null) {
            throw new NullDomainException("Mode domain is null!");
        }

        try {
            this.jdbcTemplate.update(queryManager.getQuery("modes.save"), new Object[]{
                    mode.getModeName(),
                    mode.getActivationStatus(),
                    mode.getUpLimTemperature(),
                    mode.getLowLimTemperature(),
                    mode.getUpLimHumidity(),
                    mode.getLowLimHumidity(),
                    mode.getEngineSpeed(),
                    mode.getWorkTime()
            });
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request for adding new mode into database!");
        }
    }

    /**
     * Method for removing a mode from the database using a unique identifier.
     * @param id unique identifier (number)
     * @throws DatabaseException
     */
    @Override
    public void delete(final Integer id) throws DatabaseException {
        if (id == null) {
            throw new NullKeyException("Id for delete mode from database is null!");
        }

        try {
            this.jdbcTemplate.update(queryManager.getQuery("modes.delete.id"), id);
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request on delete mode from database!");
        }
    }

    /**
     * Method for updating the mode in the database.
     * @param mode update mode
     * @throws DatabaseException
     */
    @Override
    public void update(final Mode mode) throws DatabaseException {
        if (mode == null) {
            throw new NullDomainException("Mode domain is null!");
        }

        try {
            this.jdbcTemplate.update(queryManager.getQuery("modes.update.id"), new Object[]{
                    mode.getModeName(),
                    mode.getActivationStatus(),
                    mode.getUpLimTemperature(),
                    mode.getLowLimTemperature(),
                    mode.getUpLimHumidity(),
                    mode.getLowLimHumidity(),
                    mode.getEngineSpeed(),
                    mode.getWorkTime(),
                    mode.getId()
            });
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request for update mode into database!");
        }
    }

    /**
     * Method for obtaining a mode by a unique identifier.
     * @param id unique identificator
     * @return режим
     * @throws DatabaseException
     */
    @Override
    public Mode getById(final Integer id) throws DatabaseException {
        if (id == null) {
            throw new NullKeyException("Id for getting mode from database is null!");
        }

        Mode mode;
        try {
            mode = this.jdbcTemplate.queryForObject(queryManager.getQuery("modes.get.id"), new BeanPropertyRowMapper<Mode>(Mode.class), id);
            return  mode;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    /**
     * Method for obtaining the mode according to the current activation status.
     * If the activation status is the same for several modes, the first one will return.
     * The method is necessary for the selection of the activated mode.
     * @param activationStatus activation status (true or false)
     * @return activated mode
     * @throws DatabaseException
     */
    public Mode getByActivationStatus(final Boolean activationStatus) throws DatabaseException {
        if (activationStatus == null) {
            throw new NullKeyException("Activation status for get mode is null!");
        }

        Mode mode;
        try {
            mode = this.jdbcTemplate.queryForObject(queryManager.getQuery("modes.get.activation.status"), new BeanPropertyRowMapper<Mode>(Mode.class), activationStatus);
            return  mode;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    /**
     * Method for obtaining all modes from the database.
     * @return list of modes
     * @throws DatabaseException
     */
    @Override
    public List<Mode> getAll() throws DatabaseException {
        try {
            return this.jdbcTemplate.query(queryManager.getQuery("modes.get.all"), new BeanPropertyRowMapper<Mode>(Mode.class));
        } catch (Exception e) {
            log.error("Failed to execute the request for getting all modes from database!");
            return Collections.<Mode>emptyList();
        }
    }

    /**
     * Method for obtaining the number of modes in the database.
     * @return number of modes (number)
     * @throws DatabaseException
     */
    @Override
    public Integer getCount() throws DatabaseException {
        Integer countData = 0;

        try {
            countData = this.jdbcTemplate.queryForObject(queryManager.getQuery("modes.get.count"), Integer.class);
            return countData;
        } catch (Exception e) {
            log.error(e);
            throw new ErrorQueryException("Failed to execute the request for the number of rows in the database!");
        }
    }
}
