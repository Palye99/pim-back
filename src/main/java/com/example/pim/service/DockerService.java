package com.example.pim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DockerService {

    private static final Logger logger = LoggerFactory.getLogger(DockerService.class);

    public DockerService(){
    }

    public Boolean initDocker(String choice){
        logger.info("Lancement du docker : " + choice);
        return true;
    }
}
