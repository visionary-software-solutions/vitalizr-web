package visionary.software.vitalizr.web;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum VitalizrClient {
    LIST_WEIGHTS(13339),
    LIST_BMIS(13341);

    private int port;

    VitalizrClient(final int port) {
        this.port = port;
    }

    <T extends Vital> List<T> fetchVitals(final String id) {
        try (final Socket sock = new Socket(InetAddress.getLocalHost(), port);
             final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
             final BufferedReader s = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {
            final String toSend = String.format("%s\u0004", id);
            out.write(toSend);
            out.flush();
            final String received = s.readLine().trim().replace("\u0004", "");
            final String[] weights = received.split("\u0023");
            return Arrays.stream(weights).map(w -> (T) getVital(id, w)).collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends Vital> T getVital(final String id, final String w) {
        switch (this) {
            case LIST_WEIGHTS: return (T) Weight.fromString(w, id);
            case LIST_BMIS: return (T) BodyMassIndex.fromString(w, id);
            default: throw new UnsupportedOperationException();
        }

    }
}
