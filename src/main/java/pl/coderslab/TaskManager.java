package pl.coderslab;

public class TaskManager {
    public static void main(String[] args) {
        showOptions();
    }

    public static void showOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }
}
