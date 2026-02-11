package ru.itmo.lab1.domain;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public record Crew(List<Person> members) {
  public Crew {
    if (members == null) throw new IllegalArgumentException("members must not be null");
    if (members.size() != 4) {
      throw new IllegalArgumentException("crew size must be 4");
    }
    Set<Person> uniq = new HashSet<>(members);
    if (uniq.size() != members.size()) {
      throw new IllegalArgumentException("crew members must be unique");
    }
  }
}
