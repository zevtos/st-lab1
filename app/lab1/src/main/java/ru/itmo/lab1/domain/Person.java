package ru.itmo.lab1.domain;

public record Person(String id) {
  public Person {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("person id must be non-empty");
    }
  }
}
