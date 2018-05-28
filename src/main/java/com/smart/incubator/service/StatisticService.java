package com.smart.incubator.service;

import com.smart.incubator.dao.StatisticDao;
import com.smart.incubator.domain.Statistic;
import com.smart.incubator.exception.NullDomainException;
import com.smart.incubator.exception.NullKeyException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Service for working with statistics. Performing basic CRUD operations.
 *
 * @author Tsanda Andrej
 */
@Service("statisticService")
public class StatisticService {
    private static final Logger LOG = Logger.getLogger(StatisticService.class);

    @Autowired
    private StatisticDao statisticDao;


    @Transactional
    public void saveStatistic(Statistic statistic) {
        if (statistic == null) {
            throw new NullDomainException("New statistic for adding into database is NULL!");
        }

        try {
            statisticDao.save(statistic);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Transactional
    public void deleteStatistic(Integer id) {
        if (id == null) {
            throw new NullKeyException("Identificator for deleting statistic from database is NULL!");
        }

        try {
            statisticDao.delete(id);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Transactional
    public void updateStatistic(Statistic statistic) {
        if (statistic == null) {
            throw new NullDomainException("Custom statistic for updating info into database is NULL!");
        }

        try {
            statisticDao.update(statistic);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Transactional
    public Statistic getLastStatistic() {
        try {
            return statisticDao.getLast();
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }

    @Transactional
    public List<Statistic> getAllStatistic() {
        try {
            return statisticDao.getAll();
        } catch (Exception e) {
            LOG.error(e);
            return Collections.<Statistic>emptyList();
        }
    }

    @Transactional
    public Integer getCountStatistic() {
        try {
            return statisticDao.getCount();
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }
}
