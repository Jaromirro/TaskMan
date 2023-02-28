package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;
    static Path path1 = Paths.get("tasks.csv");


    public static void main(String[] args) throws IOException {
        tasks();
        menu();

    }

    public static void menu() {

        String[] menu = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option");
        System.out.print(ConsoleColors.RESET + "");
        for (String x : menu) {
            System.out.println(x);
        }
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        System.out.println("");
        switch (input) {
            case "add":
                addTask();
                break;
            case "remove":
                remove();
                break;
            case "list":
                list();
                break;
            case "exit":
                exit();
                break;
// other options
            default:
                System.out.println("Please select a correct option.");
        }


//    public static void Scanner() throws IOException {
//        Scanner scan = new Scanner(System.in);
//        String input = scan.next();
//        System.out.println("");
//        switch (input) {
//            case "add":
//                addTask();
//                break;
//            case "remove":
//                remove();
//                break;
//            case "list":
//                list();
//                break;
//            case "exit":
//                exit();
//                break;
//// other options
//            default:
//                System.out.println("Please select a correct option.");
//        }

    }

    public static void remove() {
        Scanner scan = new Scanner(System.in);
        System.out.println("remove");
        System.out.println("Please select number to remove");
        int erase = scan.nextInt();
        String[][] result = ArrayUtils.remove(tasks, erase - 1);
        tasks = result;
        list();
        menu();


    }

    public static void list() {
        System.out.println("list");
        int i = 1;
        for (String[] row : tasks) {
            System.out.print(i++ + " | ");
            for (String column : row) {
                System.out.print("| " + column + " ");
            }
            System.out.println();
        }
        menu();

    }

    public static void exit() {
        System.out.println("exit");
        List<String> outList = new ArrayList<>();
        for (String[] row : tasks) {
            StringBuilder reading = new StringBuilder();
            for (String column : row) {
                reading.append(column + ", ");
            }
            outList.add(reading.toString());
        }
        try {
            Files.write(path1, outList);
        } catch (IOException ex) {
            System.out.println("Nie można zapisać pliku.");
        }

        System.out.println(ConsoleColors.RED + "Bye, Bye");
    }

    public static void addTask() {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        String[][] destination = new String[tasks.length + 1][3];
        for (int i = 0; i < tasks.length; ++i) {
            destination[i] = new String[tasks[i].length];
            System.arraycopy(tasks[i], 0, destination[i], 0, destination[i].length);
        }
        System.out.println("Please add task description");
        String data1 = scan.next();
        System.out.println("Please add task due date");
        String data2 = scan2.next();
        System.out.println("Is your task is important: true/false");
        String data3 = scan3.next();
        destination[destination.length - 1][0] = data1;
        destination[destination.length - 1][1] = data2;
        destination[destination.length - 1][2] = data3;
        int i = 1;
        for (String[] row : destination) {
            System.out.print(i++ + " | ");
            for (String column : row) {
                System.out.print("| " + column + " ");
            }
            System.out.println();
        }
        tasks = destination;
        menu();
    }

    static void tasks() throws IOException {
        long a = Files.lines(path1).count();
        String[][] list = new String[(int) a][3];
        for (int i = 0; i < a; i++) {
            String[] line = Files.readAllLines(path1).toArray(new String[i]);
            String[] row = line[i].split(", ");
            for (int j = 0; j < 3; j++) {
                list[i][j] = row[j];
            }
        }
        tasks = list;
    }
}




