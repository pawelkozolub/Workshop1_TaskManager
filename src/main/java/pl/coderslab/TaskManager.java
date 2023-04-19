package pl.coderslab;

import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        boolean isExit = false;

        while (!isExit) {
            showOptions();
            String input = consoleInput("Enter selected option");
            //System.out.println(input);

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
}
