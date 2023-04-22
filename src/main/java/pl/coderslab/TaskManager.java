package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;
    static String pathToTasksFile = "./src/main/resources/tasks.csv";

    public static void main(String[] args) {
        boolean isExit = false;

        readTasks();

        while (!isExit) {
            showOptions();
            String input = consoleInput("Enter selected option");
            System.out.println(input);

            switch (input) {
                case "exit":
                    isExit = true;
                    writeTasks();
                    System.out.println(
                            ConsoleColors.RED + "Bye, bye..." + ConsoleColors.RESET
                    );
                    break;

                case "add":
                    addTask();
                    break;

                case "remove":
                    removeTask();
                    break;

                case "list":
                    listTasks();
                    break;

                default:
                    System.out.println(
                            ConsoleColors.RED + "Please select a correct option" + ConsoleColors.RESET
                    );
            }
        }

    }

    public static void showOptions() {
        System.out.println();
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
        System.out.println();
    }

    public static String consoleInput(String message) {
        System.out.println(message);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void readTasks() {
        tasks = new String[0][0];
        File tasksFile = new File(pathToTasksFile);

        try {
            Scanner fileScan = new Scanner(tasksFile);
            while (fileScan.hasNextLine()) {
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[tasks.length - 1] = new String[3];
                String[] lineParts = fileScan.nextLine().split(", ");
                for (int i = 0; i < tasks[tasks.length - 1].length; i++) {
                    tasks[tasks.length - 1][i] = lineParts[i];
                }
                //System.out.println(Arrays.toString(tasks[tasks.length - 1]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Tasks file do not exists");
            e.printStackTrace();
        }
    }

    public static void listTasks() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }

    public static void removeTask() {
        boolean quit = false;
        String taskToRemove;

        while (!quit) {
            if (tasks.length < 1) {
                System.out.println("No task to remove, task list is already empty.");
                quit = true;

            } else {
                taskToRemove = consoleInput("Please select number to remove task or 'quit' to return to options");
                if (NumberUtils.isParsable(taskToRemove)) {
                    int taskNumber = Integer.parseInt(taskToRemove);
                    try {
                        tasks = ArrayUtils.remove(tasks, taskNumber);
                        System.out.println("Task " + taskNumber + " has been removed from list.");
                        quit = true;

                    } catch (IndexOutOfBoundsException e1) {
                        System.out.println(ConsoleColors.RED + "Incorrect argument passed." + ConsoleColors.RESET);
                        System.out.println("Please give a value within 0 and max task number.");
                    }

                } else if (taskToRemove.equals("quit")) {
                    quit = true;

                } else {
                    System.out.println(ConsoleColors.RED + "Incorrect argument passed." + ConsoleColors.RESET);
                    System.out.println("Please give a numerical value >= 0.");
                }
            }
        }
    }

    public static void addTask() {
        String taskName = consoleInput("Please add task description:");
        String taskDate = consoleInput("Please add task due date:");
        String taskImportance = consoleInput("Is your task important: true/false");

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = taskName;
        tasks[tasks.length - 1][1] = taskDate;
        tasks[tasks.length - 1][2] = taskImportance;

        System.out.println("\nNew task created.");
    }

    public static void writeTasks() {
        try (FileWriter fileWriter = new FileWriter(pathToTasksFile, false)) {
            for (int i = 0; i < tasks.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(tasks[i][0]).append(", ").append(tasks[i][1]).append(", ").append(tasks[i][2]);
                if (i < tasks.length - 1) {
                    sb.append("\n");    // newline symbol is not added to last file line
                }
                fileWriter.append(sb);
            }
            System.out.println("Tasks file written.");
        } catch (IOException ex) {
            System.out.println("Error: tasks not saved into file.");
            ex.printStackTrace();
        }
    }
}
