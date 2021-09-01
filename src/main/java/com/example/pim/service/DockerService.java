package com.example.pim.service;

import com.example.pim.dto.ResultCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DockerService {

    private static final Logger logger = LoggerFactory.getLogger(DockerService.class);

    public DockerService(){
    }

    public ResultCommand initDocker(int choice) throws Exception {
        if (choice > 4 || choice < 1) {
            throw new Exception("Impossible de reconnaitre le docker demandé");
        }

        try {
            logger.info("Lancement du docker : " + choice);

            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");

            String homeDirectory = System.getProperty("user.home");
            Process process = null;
            if (isWindows) {
                switch(choice) {
                    case 1 :
                        process = Runtime.getRuntime()
                                .exec("cmd.exe docker run -dit javapim");
                        break;
                    case 2 :
                        process = Runtime.getRuntime()
                                .exec(String.format("cmd.exe /c dir %s", homeDirectory));
                        break;
                    case 3 :
                        process = Runtime.getRuntime()
                                .exec("cmd.exe docker run -dit bashpim");
                        break;
                    case 4 :
                        process = Runtime.getRuntime()
                                .exec("cmd.exe docker run -dit pythonpim");
                        break;
                }
            } else {
                switch(choice) {
                    case 1 :
                        process = Runtime.getRuntime()
                                .exec("sh docker run -dit javapim");
                        break;
                    case 2 :
                        process = Runtime.getRuntime()
                                .exec(String.format("sh -c ls %s", homeDirectory));
                        break;
                    case 3 :
                        process = Runtime.getRuntime()
                                .exec("sh docker run -dit bashpim");
                        break;
                    case 4 :
                        process = Runtime.getRuntime()
                                .exec("sh docker run -dit pythonpim");
                        break;
                }
            }

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            ResultCommand resultCommand = new ResultCommand();

            // Read the output from the command
            System.out.println("Here is the standard output of the command:\n");

            String s = null;

            while ((s = stdInput.readLine()) != null) {
                resultCommand.setResult(resultCommand.getResult() + s);
                System.out.println(s);
            }

            // Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                resultCommand.setError(resultCommand.getError() + "\n" + s);
                System.out.println(s);
            }


//            StreamGobbler streamGobbler =
//                    new StreamGobbler(process.getInputStream(), System.out::println);
//
//            System.out.println(" zserfezrzerzerzer " + streamGobbler);
//
//            Executors.newSingleThreadExecutor().submit(streamGobbler);
//            int exitCode = process.waitFor();
//            assert exitCode == 0;
//
//            System.out.println(" zserfezrzerzerzer " + streamGobbler);

            return resultCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Erreur lors de l'execution du docker");
    }

    public ResultCommand shellCommand(String command) throws Exception {
        try {
            logger.info("command docker from shell");

            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");

            String homeDirectory = System.getProperty("user.home");
            Process process = null;
            if (isWindows) {
                process = Runtime.getRuntime()
                    .exec(String.format("cmd.exe /c %s", command));
            } else {
                process = Runtime.getRuntime()
                    .exec(String.format("sh -c %s", command));
            }

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            ResultCommand resultCommand = new ResultCommand();

            // Read the output from the command
            System.out.println("Here is the standard output of the command:\n");

            String s = null;

            while ((s = stdInput.readLine()) != null) {
                resultCommand.setResult(resultCommand.getResult() + s);
                System.out.println(s);
            }

            // Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                resultCommand.setError(resultCommand.getError() + "\n" + s);
                System.out.println(s);
            }
            return resultCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Erreur lors de l'execution du docker");
    }

    public ResultCommand dockerCommand(String id, String command) throws Exception {
        try {
            logger.info("command docker from shell");

            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");

            String homeDirectory = System.getProperty("user.home");
            Process process = null;
            if (isWindows) {
                process = Runtime.getRuntime()
                        .exec(String.format("cmd.exe /c docker exec -it %s %s", id, command));
            } else {
                process = Runtime.getRuntime()
                        .exec(String.format("sh -c docker exec -it %s %s", id, command));
            }

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            ResultCommand resultCommand = new ResultCommand();

            // Read the output from the command
            System.out.println("Here is the standard output of the command:\n");

            String s = null;

            while ((s = stdInput.readLine()) != null) {
                resultCommand.setResult(resultCommand.getResult() + s);
                System.out.println(s);
            }

            // Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                resultCommand.setError(resultCommand.getError() + "\n" + s);
                System.out.println(s);
            }
            return resultCommand;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new Exception("Erreur lors de l'execution du docker");
    }
}
