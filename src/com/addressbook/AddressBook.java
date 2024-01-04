package com.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void displayContacts() {
        System.out.println("All contacts in the address book:");
        for (Contact contact : contacts) {
            System.out.println(contact.getFirstName() + " " + contact.getLastName());
            // Display other details as needed
        }
    }

    public List<Contact> searchContactsInCityOrState(String location) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(location) || contact.getState().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    public List<Contact> getContactsInCity(String city) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> getContactsInState(String state) {
        return contacts.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
}
