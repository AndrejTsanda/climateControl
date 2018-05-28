package com.smart.incubator.service;

import com.smart.incubator.dao.ModeDao;
import com.smart.incubator.domain.Mode;
import com.smart.incubator.exception.NullDomainException;
import com.smart.incubator.exception.NullKeyException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Service for working with modes. Performing basic CRUD operations.
 *
 * @author Tsanda Andrej
 */
@Service("modeService")
public class ModeService {
    private static final Logger LOG = Logger.getLogger(ModeService.class);

    @Autowired
    private ModeDao modeDao;


    @Transactional
    public void saveMode(Mode mode) {
        if (mode == null) {
            throw new NullDomainException("New mode for adding into database is NULL!");
        }

        try {
            modeDao.save(mode);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Transactional
    public void deleteMode(Integer id) {
        if (id == null) {
            throw new NullKeyException("Identificator for deleting mode from database is NULL!");
        }

        try {
            modeDao.delete(id);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Transactional
    public void updateMode(Mode mode) {
        if (mode == null) {
            throw new NullDomainException("Custom mode for updating info into database is NULL!");
        }

        try {
            modeDao.update(mode);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Transactional
    public Mode getModeById(Integer id) {
        if (id == null) {
            throw new NullKeyException("Identificator for getting mode from database is NULL!");
        }

        try {
            return modeDao.getById(id);
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }

    @Transactional
    public Mode getModeByActivationStatus(Boolean activationStatus) {
        if (activationStatus == null) {
            throw new NullKeyException("Activation status for getting mode from database is NULL!");
        }

        try {
            return modeDao.getByActivationStatus(activationStatus);
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }

    @Transactional
    public List<Mode> getAllMode() {
        try {
            return modeDao.getAll();
        } catch (Exception e) {
            LOG.error(e);
            return Collections.<Mode>emptyList();
        }
    }

    @Transactional
    public Integer getCountMode() {
        try {
            return modeDao.getCount();
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }
}
