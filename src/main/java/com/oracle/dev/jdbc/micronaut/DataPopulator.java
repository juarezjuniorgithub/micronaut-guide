package com.oracle.dev.jdbc.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import jakarta.transaction.Transactional;
import java.util.Arrays;

import com.oracle.dev.jdbc.micronaut.domain.Thing;
import com.oracle.dev.jdbc.micronaut.repository.ThingRepository;

@Singleton
@Requires(notEnv = "test")
public class DataPopulator {

    private final ThingRepository thingRepository;

    public DataPopulator(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    @EventListener
    @Transactional
    void init(StartupEvent event) {
        // clear out any existing data
        thingRepository.deleteAll();

        // create data
        Thing juarez = new Thing("Juarez");
        Thing kuassi = new Thing("Kuassi");
        Thing paul = new Thing("Paul");
        thingRepository.saveAll(Arrays.asList(juarez, kuassi, paul));
    }
}