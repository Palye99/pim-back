package com.example.pim.service;

import com.example.pim.bo.StreamGobbler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Executors;

@Service
public class DockerService {

    private static final Logger logger = LoggerFactory.getLogger(DockerService.class);

    public DockerService(){
    }

    public Boolean initDocker(String choice) throws Exception {
        if (choice == null || choice.isBlank() || choice.isEmpty()) {
            throw new Exception("Impossible de reconnaitre le docker demandé");
        }

        try {
            logger.info("Lancement du docker : " + choice);

//            Process p = Runtime.getRuntime().exec("docker images");
//            logger.info("Docker : " + p.toString());

            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");

            String homeDirectory = System.getProperty("user.home");
            Process process;
            if (isWindows) {
                process = Runtime.getRuntime()
                        .exec(String.format("cmd.exe /c dir %s", homeDirectory));
            } else {
                process = Runtime.getRuntime()
                        .exec(String.format("sh -c ls %s", homeDirectory));
            }
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            Executors.newSingleThreadExecutor().submit(streamGobbler);
            int exitCode = process.waitFor();
            assert exitCode == 0;

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Erreur lors de l'execution du docker");
    }
}
