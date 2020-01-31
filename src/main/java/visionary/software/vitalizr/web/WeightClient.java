package visionary.software.vitalizr.web;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("/weight")
public interface WeightClient {
    @Get
    List<Weight> list();
}
