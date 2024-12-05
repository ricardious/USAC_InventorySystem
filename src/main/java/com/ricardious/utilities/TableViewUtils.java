package com.ricardious.utilities;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Map;

public class TableViewUtils {
    /**
     * Sets up search functionality for a TableView by filtering data based on input in a TextField.
     * This method listens to changes in the search field and filters items in the TableView according
     * to specified properties in each item. It supports dynamic sorting and binding to the TableView's comparator.
     *
     * @param searchField the TextField where users input search text
     * @param tableView the TableView to be filtered and sorted
     * @param data the original data list to be displayed in the TableView
     * @param searchProperties the properties within each item to be searched for matches with the input text
     * @param <T> the type of items in the TableView
     */
    public static  <T> void setupTableSearch(TextField searchField, TableView<T> tableView, ObservableList<T> data,
                                      String... searchProperties) {
        // Initializes FilteredList with all data, initially showing all items
        FilteredList<T> filteredData = new FilteredList<>(data, p -> true);

        // Adds a listener to the search field to detect text input changes
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If the search text is empty, all items are displayed
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Checks each specified property for a match with the search text
                for (String property : searchProperties) {
                    String value = "";
                    if (item instanceof Map) {
                        // If item is a Map, retrieve value using the property key
                        value = String.valueOf(((Map<?, ?>) item).get(property));
                    } else {
                        try {
                            // If item is not a Map, retrieve value through reflection
                            value = String.valueOf(item.getClass().getField(property).get(item));
                        } catch (Exception e) {
                            continue; // Skips to the next property if error occurs
                        }
                    }

                    // If the property's value contains the search text, the item is shown
                    if (value.toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                }
                // If no properties match, item is filtered out
                return false;
            });
        });

        // Creates a SortedList linked to the FilteredList for automatic sorting
        SortedList<T> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // Binds the sorted and filtered data to the table view
        tableView.setItems(sortedData);
    }
}
