package com.smartValue.web.servelets;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ProcessManager {
    private static final Map<String, ProcessData> processes = new ConcurrentHashMap<>();

    public static String startProcess(String command) throws IOException {
        String identifier = UUID.randomUUID().toString();
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        ProcessData processData = new ProcessData(process);
        processes.put(identifier, processData);

        // Start a thread to write output directly to the output stream
        new Thread(() -> {
            try (OutputStream outputStream = processData.getOutputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    outputStream.write(line.getBytes());
                    outputStream.write('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        return identifier;
    }

    public static void killProcess(String identifier) throws IOException {
        ProcessData processData = processes.get(identifier);
        if (processData != null) {
            processData.getProcess().destroy();
            processData.getOutputStream().close(); // Close the output stream
            processes.remove(identifier);
        }
    }

    public static OutputStream getProcessOutputStream(String identifier) {
        ProcessData processData = processes.get(identifier);
        return processData != null ? processData.getOutputStream() : null;
    }

    public static boolean isProcessRunning(String identifier) {
        ProcessData processData = processes.get(identifier);
        return processData != null && processData.getProcess().isAlive();
    }
}

class ProcessData {
    private final Process process;
    private final PipedOutputStream outputStream = new PipedOutputStream();
    private final List<String> outputLines = new ArrayList<>();
    private volatile boolean paused = false;

    public ProcessData(Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    public PipedOutputStream getOutputStream() {
        return outputStream;
    }

    public synchronized void appendOutput(String line) {
        outputLines.add(line);
    }

    public synchronized List<String> getOutputLines() {
        return new ArrayList<>(outputLines); // Return a copy to avoid concurrent modification issues
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
    }

    public synchronized boolean isPaused() {
        return paused;
    }
}
