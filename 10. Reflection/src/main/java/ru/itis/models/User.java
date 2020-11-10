package ru.itis.models;

/**
 * @author Anvar Khasanov
 * group 11-905
 */

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isWorker;

    public User() { }

    public User(Long id, String firstName, String lastName, Boolean isWorker) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isWorker = isWorker;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getWorker() {
        return isWorker;
    }

    public void setWorker(Boolean worker) {
        isWorker = worker;
    }
}

