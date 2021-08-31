package com.example.pim.rest;

import com.example.pim.service.DockerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/docker")
public class DockerController {

    private final DockerService dockerService;


    public DockerController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @CrossOrigin()
    @RequestMapping(value = "/{dockerChoice}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> initDocker(@PathVariable String dockerChoice) throws Exception {
        return Optional
                .ofNullable(dockerService.initDocker(dockerChoice))
                .map(list -> ResponseEntity.ok().body(list))          //200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
