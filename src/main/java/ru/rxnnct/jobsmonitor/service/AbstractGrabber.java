package ru.rxnnct.jobsmonitor.service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractGrabber {

    abstract void grab();

    void saveErrorLog(String text){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);

        try(FileWriter fileWriter = new FileWriter("logs" + File.separator + "Err" + this.getClass().getSimpleName() + ".log", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter out = new PrintWriter(bufferedWriter))
        {
            out.println(currentTime + ", " + this.getClass().getSimpleName() + ": " + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
