package com.progressoft;

import java.util.*;

public class InsertionOrderSorter {
    private final Map<String, Set<String>> tables;

    public InsertionOrderSorter(Map<String, Set<String>> tables) {
        this.tables = tables;
    }

    public List<String> calculateInsertionOrder() {
        List<String> sortedOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> allTables = new ArrayList<>(tables.keySet());

        // Track all tables and ensure unrelated tables are first in the order
        for (String table : allTables) {
            if (tables.get(table).isEmpty()) {
                sortedOrder.add(table);
                visited.add(table);
            }
        }

        // Then, process tables with dependencies
        for (String table : allTables) {
            if (!visited.contains(table)) {
                applySort(table, visited, sortedOrder);
            }
        }

        return sortedOrder;
    }

    private void applySort(String table, Set<String> visited, List<String> sortedOrder) {

        if (visited.contains(table)){
            return;
        }

        visited.add(table);

        for (String dependency : tables.get(table)) {
            applySort(dependency, visited, sortedOrder);
        }

        sortedOrder.add(table);
    }
}
