package com.muhsener98.task.tracker.cli;

import org.example.task.tracker.domain.dto.TaskResponse;

import java.util.List;
import java.util.function.Function;

public class TableViewPrinter {

    public static void printTableView(List<TaskResponse> tasks) {
        int idLength = 36;
        int descriptionLength = getMaxStringLength(tasks, TaskResponse::getDescription);
        int statusLength = getMaxStringLength(tasks, TaskResponse::getStatus);
        int createdAtLength = getMaxStringLength(tasks, (task) -> task.getCreatedAt().toString());
        int updatedAtLength = getMaxStringLength(tasks, (task) -> task.getUpdatedAt().toString());

        String idHeader = padString("ID", idLength);
        String descriptionHeader = padString("Description", descriptionLength);
        String statusHeader = padString("Status", statusLength);
        String createdAtHeader = padString("Created At", createdAtLength);
        String updatedAtHeader = padString("Updated At", updatedAtLength);

        System.out.println("+" + repeatString("-", idLength + 2) + "+" + repeatString("-", descriptionLength + 2) + "+" + repeatString("-", statusLength + 2) + "+" + repeatString("-", createdAtLength + 2) + "+" + repeatString("-", updatedAtLength + 2) + "+");
        System.out.println("| " + idHeader + " | " + descriptionHeader + " | " + statusHeader + " | " + createdAtHeader + " | " + updatedAtHeader + " |");
        System.out.println("+" + repeatString("-", idLength + 2) + "+" + repeatString("-", descriptionLength + 2) + "+" + repeatString("-", statusLength + 2) + "+" + repeatString("-", createdAtLength + 2) + "+" + repeatString("-", updatedAtLength + 2) + "+");

        for (TaskResponse task : tasks) {
            String id = padString(task.getUuid().toString(), idLength);
            String description = padString(task.getDescription(), descriptionLength);
            String status = padString(task.getStatus(), statusLength);
            String createdAt = padString(task.getCreatedAt().toString(), createdAtLength);
            String updatedAt = padString(task.getUpdatedAt().toString(), updatedAtLength);

            System.out.println("| " + id + " | " + description + " | " + status + " | " + createdAt + " | " + updatedAt + " |");
        }

        System.out.println("+" + repeatString("-", idLength + 2) + "+" + repeatString("-", descriptionLength + 2) + "+" + repeatString("-", statusLength + 2) + "+" + repeatString("-", createdAtLength + 2) + "+" + repeatString("-", updatedAtLength + 2) + "+");
    }

    private static int getMaxStringLength(List<TaskResponse> tasks, Function<TaskResponse, String> function) {
        int maxLength = 0;
        for (TaskResponse task : tasks) {
            int length = function.apply(task).length();
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return Math.max(maxLength , "Description".length());
    }

    private static String padString(String str, int length) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

}
