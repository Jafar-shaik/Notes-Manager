package com.notesmanager;

import java.io.*;
import java.util.Scanner;

public class NotesManager {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=== Text-Based Notes Manager ===");

        while (true) {
            System.out.println("\n1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the note.");
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No notes found.");
            return;
        }

        System.out.println("\n=== Saved Notes ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(count + ". " + line);
                count++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading notes.");
            e.printStackTrace();
        }
    }
}
