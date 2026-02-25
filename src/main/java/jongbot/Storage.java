package jongbot;

import jongbot.exceptions.JongExceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String tasksFilePath;

    public Storage(String tasksFilePath) {
        this.tasksFilePath = tasksFilePath;
        ensureFileExists();
    }

    public ArrayList<Task> load() throws JongExceptions {
        ArrayList<Task> loaded = new ArrayList<>();
        File file = new File(tasksFilePath);
        if (!file.exists()) {
            return loaded;
        }
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                Task task = parseFileLine(sc.nextLine());
                if (task != null) {
                    loaded.add(task);
                }
            }
            return loaded;
        } catch (FileNotFoundException e) {
            throw new JongExceptions("File not found: " + tasksFilePath);
        }
    }

    public void save(TaskList tasks) throws JongExceptions {
        try (FileWriter fw = new FileWriter(tasksFilePath)) {
            for (Task task : tasks.getTasks()) {
                fw.write(task.toDataString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new JongExceptions("Error writing to file: " + tasksFilePath);
        }
    }

    private void ensureFileExists() {
        try {
            File file = new File(tasksFilePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new IOException("Failed to create directory: "
                            + parent.getAbsolutePath());
                }
            }

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create file: "
                            + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Critical error initializing storage", e);
        }
    }

    private Task parseFileLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description, parts[3], parts[4]);
            }
            break;
        default:
            break;
        }

        if (task != null && isDone) {
            task.markTask();
        }
        return task;
    }
}
