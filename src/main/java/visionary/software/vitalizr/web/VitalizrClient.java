package visionary.software.vitalizr.web;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.logging.Logger;

public enum VitalizrClient {
    ADD_WEIGHT(13338),
    LIST_WEIGHT(13339),
    ADD_BMI(13340),
    LIST_BMI(13341),
    ADD_FAT(13342),
    LIST_FAT(13343),
    ADD_WATER(13344),
    LIST_WATER(13345),
    ADD_SUGAR(13346),
    LIST_SUGAR(13347),
    ADD_BP(13348),
    LIST_BP(13349),
    ADD_TEMP(13350),
    LIST_TEMP(13351),
    ADD_O2(13352),
    LIST_O2(13353),
    ADD_PULSE(13354),
    LIST_PULSE(13355);

    private static final Logger log = Logger.getLogger(VitalizrClient.class.getSimpleName());
    private int port;

    VitalizrClient(final int port) {
        this.port = port;
    }

    public String forId(final String id) {
        return doRequestReply(this.port, this.createRequest(new ArrayDeque<>(){{add(id);}}));
    }

    public static void main(final String[] args) {
        final Deque<String> deque = new ArrayDeque<>(Arrays.asList(args == null ? new String[0] : args));
        final String toDispatch = String.format("%s_%s", deque.pop(), deque.pop());
        final VitalizrClient client = VitalizrClient.valueOf(toDispatch.toUpperCase());
        client.execute(deque);
    }

    private void execute(final Deque<String> deque) {
        doRequestReply(port, createRequest(deque));
    }

    private String createRequest(final Deque<String> deque) {
        switch(this) {
            case ADD_WEIGHT:
            case ADD_TEMP:
                return String.format("%s&%s&%s\u0004", deque.pop(), deque.pop(), deque.pop());
            case LIST_WEIGHT:
            case LIST_BMI:
            case LIST_FAT:
            case LIST_WATER:
            case LIST_SUGAR:
            case LIST_BP:
            case LIST_TEMP:
            case LIST_O2:
            case LIST_PULSE:
                return String.format("%s\u0004", deque.pop());
            case ADD_BMI:
            case ADD_FAT:
            case ADD_WATER:
            case ADD_SUGAR:
            case ADD_BP:
            case ADD_O2:
            case ADD_PULSE:
                return String.format("%s&%s\u0004", deque.pop(), deque.pop());
            default: throw new UnsupportedOperationException("We do not support " + name());
        }
    }

    private static String doRequestReply(final int port, final String request) {
        try (final Socket sock = new Socket(InetAddress.getLocalHost(), port);
             final BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
             final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {
            log.info("Sending request " + request);
            out.write(request);
            out.flush();
            final String received = in.readLine();
            log.info("received response  " + received);
            return received;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
