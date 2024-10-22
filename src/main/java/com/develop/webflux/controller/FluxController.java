package com.develop.webflux.controller;

import com.develop.webflux.context.FluxRequest;
import com.develop.webflux.context.FluxResponse;
import com.develop.webflux.repository.FluxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/webFlux")
public class FluxController {
    private final FluxRepository fluxRepository;

    @GetMapping("/getCard/{id}")
    public Mono<ResponseEntity<FluxResponse>> getCard(@PathVariable String id) {
        log.info("Getting card ...");
        try {
            FluxResponse response = fluxRepository.searchCard(id);
            if (response != null) {
                return Mono.just(ResponseEntity.ok(response));
            } else {
                return Mono.just(ResponseEntity.notFound().build());
            }
        } catch (Exception e) {
            log.error("Error while getting card: ", e);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
        }
    }

    @PostMapping("/postCard")
    public Mono<ResponseEntity<String>> postCard(@RequestBody FluxRequest request) {
        log.info("Posting card ...");
        try {
            boolean inserted = fluxRepository.insertCard(request);
            if (inserted) {
                return Mono.just(ResponseEntity.ok("Card created successfully!"));
            } else {
                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Card creation failed."));
            }
        } catch (Exception e) {
            log.error("Error while posting card: ", e);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Task for logging: " + e.getCause()));
        }
    }

    @PutMapping("/attCard/{id}")
    public Mono<ResponseEntity<String>> putCard(@RequestBody FluxRequest request, @PathVariable String id) {
        log.info("Updating card ...");
        try {
            boolean updated = fluxRepository.updateCard(request, id);
            if (updated) {
                return Mono.just(ResponseEntity.ok("Card updated successfully!"));
            } else {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found."));
            }
        } catch (Exception e) {
            log.error("Error while updating card: ", e);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Task for logging: " + e.getCause()));
        }
    }

    @DeleteMapping("/dropCard/{id}")
    public Mono<ResponseEntity<String>> dropCard(@PathVariable String id) {
        log.info("Dropping card ...");
        try {
            boolean deleted = fluxRepository.droppingCard(id);
            if (deleted) {
                return Mono.just(ResponseEntity.ok("Card deleted successfully!"));
            } else {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found."));
            }
        } catch (Exception e) {
            log.error("Error while dropping card: ", e);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Task for logging: " + e.getCause()));
        }
    }

}
