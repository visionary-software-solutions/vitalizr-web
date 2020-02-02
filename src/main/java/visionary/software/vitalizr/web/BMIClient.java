package visionary.software.vitalizr.web;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("/bmi")
public interface BMIClient {
    @Get
    List<BodyMassIndex> list();
}
