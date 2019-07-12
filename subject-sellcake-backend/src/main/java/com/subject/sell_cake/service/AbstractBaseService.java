package com.subject.sell_cake.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Van Dat
 */
public abstract class AbstractBaseService {
    
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    protected final Gson gson = new Gson();
}
