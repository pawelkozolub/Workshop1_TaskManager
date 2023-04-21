package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
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
                    System.out.println(
                            ConsoleColors.RED + "Bye, bye..." + ConsoleColors.RESET
                    );
                    break;

                case "add":
                    // to be implemented
                    break;

                case "remove":
                    // to be implemented
                    break;

                case "list":
                    // to be implemented
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
}
