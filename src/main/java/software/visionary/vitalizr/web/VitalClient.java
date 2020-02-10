package software.visionary.vitalizr.web;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("/vital")
public interface VitalClient {
    @Get("/weight/{id}")
    List<Vital> weights(@PathVariable String id);
    @Get("/bmi/{id}")
    List<Vital> bmis(@PathVariable String id);
}
