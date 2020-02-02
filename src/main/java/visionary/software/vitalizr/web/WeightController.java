package visionary.software.vitalizr.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/weight")
public class WeightController {
    @Get
    public List<Weight> list() {
        try (final Socket sock = new Socket(InetAddress.getLocalHost(), 13339);
             final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
             final BufferedReader s = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {
            final String toSend = String.format("%s\u0004", Weight.createCannedNick().lifeform);
            out.write(toSend);
            out.flush();
            final String received = s.readLine().trim().replace("\u0004", "");
            final String[] weights = received.split("\u0023");
            return Arrays.stream(weights).map(w -> Weight.fromString(w, Weight.createCannedNick().lifeform)).collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
