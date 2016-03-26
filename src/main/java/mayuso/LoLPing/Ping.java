package mayuso.LoLPing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ping {

    private static final String REGEX_PING = "time=(.*?)ms";

    public int getPing(String ip) {
        String pingCmd = "ping " + ip;

        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec(pingCmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //reads the outputs

            String inputLine = in.readLine();

            Pattern pattern = Pattern.compile(REGEX_PING);
            while (inputLine != null) {
                inputLine = in.readLine();
                if (!inputLine.isEmpty() && inputLine.startsWith("Reply")) {
                    try {
                        Matcher matcher = pattern.matcher(inputLine);
                        if (matcher.find()) {
                            String pingValue = matcher.group(1);
                            return Integer.valueOf(pingValue);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
