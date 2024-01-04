package com.addressbook;

import java.util.*;

public class AddressBookMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, AddressBook> addressBooks = new HashMap<>();

        char choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Create a new Address Book");
            System.out.println("2. Add a contact to an Address Book");
            System.out.println("3. Display contacts in an Address Book");
            System.out.println("4. Exit");
            System.out.println("5. Search persons by City or State");
            System.out.println("6. View persons by City or State");
            System.out.println("7. Get count of contacts by City or State");
            System.out.println("Enter your choice:");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the int

            switch (option) {
                case 1:
                    System.out.println("Enter a unique name for the Address Book:");
                    String bookName = scanner.nextLine();
                    AddressBook newAddressBook = new AddressBook();
                    addressBooks.put(bookName, newAddressBook);
                    System.out.println("Address Book '" + bookName + "' created successfully!");
                    break;
                case 2:
                    System.out.println("Enter the name of the Address Book:");
                    String selectedBook = scanner.nextLine();
                    AddressBook selectedAddressBook = addressBooks.get(selectedBook);

                    if (selectedAddressBook != null) {
                        System.out.println("Enter details for a new contact:");
                        // Similar code for adding contacts as previously shown
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the name of the Address Book to display contacts:");
                    String displayBook = scanner.nextLine();
                    AddressBook displayAddressBook = addressBooks.get(displayBook);

                    if (displayAddressBook != null) {
                        displayAddressBook.displayContacts();
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                case 5:
                    System.out.println("Enter the city or state to search:");
                    String searchLocation = scanner.nextLine();
                    searchPersons(addressBooks, searchLocation);
                    break;
                case 6:
                    System.out.println("View persons by (1) City or (2) State:");
                    int viewOption = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the int

                    if (viewOption == 1) {
                        System.out.println("Enter the city to view persons:");
                        String cityToView = scanner.nextLine();
                        viewPersonsByCity(addressBooks, cityToView);
                    } else if (viewOption == 2) {
                        System.out.println("Enter the state to view persons:");
                        String stateToView = scanner.nextLine();
                        viewPersonsByState(addressBooks, stateToView);
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;
                case 7:
                    System.out.println("Get count of contacts by (1) City or (2) State:");
                    int countOption = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the int

                    if (countOption == 1) {
                        System.out.println("Enter the city to get contact count:");
                        String cityCount = scanner.nextLine();
                        getCountByCity(addressBooks, cityCount);
                    } else if (countOption == 2) {
                        System.out.println("Enter the state to get contact count:");
                        String stateCount = scanner.nextLine();
                        getCountByState(addressBooks, stateCount);
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }

            System.out.println("Do you want to continue? (Y/N)");
            choice = scanner.nextLine().charAt(0);
        } while (choice == 'Y' || choice == 'y');

        scanner.close();
    }

    private static void searchPersons(Map<String, AddressBook> addressBooks, String location) {
        System.out.println("Search results:");
        addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.searchContactsInCityOrState(location).stream())
                .forEach(contact -> System.out.println(contact.getFirstName() + " " + contact.getLastName()));
    }

    private static void viewPersonsByCity(Map<String, AddressBook> addressBooks, String city) {
        Map<String, List<Contact>> cityPersonMap = new HashMap<>();

        addressBooks.values().forEach(addressBook ->
                addressBook.getContactsInCity(city).forEach(contact ->
                        cityPersonMap.computeIfAbsent(city, k -> new ArrayList<>()).add(contact)));

        if (cityPersonMap.containsKey(city)) {
            System.out.println("Persons in " + city + ":");
            cityPersonMap.get(city).forEach(person ->
                    System.out.println(person.getFirstName() + " " + person.getLastName()));
        } else {
            System.out.println("No persons found in " + city);
        }
    }

    private static void viewPersonsByState(Map<String, AddressBook> addressBooks, String state) {
        Map<String, List<Contact>> statePersonMap = new HashMap<>();

        addressBooks.values().forEach(addressBook ->
                addressBook.getContactsInState(state).forEach(contact ->
                        statePersonMap.computeIfAbsent(state, k -> new ArrayList<>()).add(contact)));

        if (statePersonMap.containsKey(state)) {
            System.out.println("Persons in " + state + ":");
            statePersonMap.get(state).forEach(person ->
                    System.out.println(person.getFirstName() + " " + person.getLastName()));
        } else {
            System.out.println("No persons found in " + state);
        }
    }

    private static void getCountByCity(Map<String, AddressBook> addressBooks, String city) {
        long count = addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContactsInCity(city).stream())
                .count();

        System.out.println("Number of contacts in " + city + ": " + count);
    }

    private static void getCountByState(Map<String, AddressBook> addressBooks, String state) {
        long count = addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContactsInState(state).stream())
                .count();

        System.out.println("Number of contacts in " + state + ": " + count);
    }
}





