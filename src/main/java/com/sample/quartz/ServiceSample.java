package com.sample.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 06/11/17.
 */
@Slf4j
@Service
public class ServiceSample {

    public void printAnything(){
      log.info("Anything");
    }

}
