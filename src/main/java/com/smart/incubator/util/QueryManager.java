package com.smart.incubator.util;

import com.smart.incubator.exception.ErrorQueryException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.cache.annotation.Cacheable;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * A simple manager for getting sql-requests from .properties files.
 * Requests are cached by Ehcache.
 *
 * @author Tsanda Andrej
 */
public class QueryManager {
    private static final Logger LOG = Logger.getLogger(QueryManager.class);

    @Autowired
    private AbstractBeanFactory abstractBeanFactory;


    /**
     * A method for obtaining a sql-query on a key from the .properties file.
     * Example:
     *  key = select.all.user
     *  This key method will lead to this kind: $ {select.all.user} and select
     *  from .properties file sql-query with this key.
     *
     * @param key key (string)
     * @return sql-query (in a string representation)
     */
    @Cacheable("sqls")
    public String getQuery(final String key) {

        if (isBlank(key)) {
            throw new ErrorQueryException("Query key is null or empty!");
        }

        LOG.debug("Key for query: [" + key + "].");

        try {
            final String sql = abstractBeanFactory.resolveEmbeddedValue("${" + key + "}");
            LOG.debug("Key = [" + key + "] has been loaded from Spring Properties.");
            return sql;
        } catch (Exception e) {
            LOG.error(e);
        }

        throw new ErrorQueryException("Query is unavailable. Key = [" + key + "].");
    }
}