package domain;

import java.io.*;
import java.util.Date;

public class MarioException extends Exception {

    public MarioException(String message) {
        super(message);
    }

    public static void addLogMessage(Exception e) throws IOException {

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String exceptionAsString = stringWriter.toString();

        File file = new File("MarioLog.txt");
        Date date = new Date();

        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(date.toString() + ": " + exceptionAsString + "\n");
        fileWriter.close();

    }

}
