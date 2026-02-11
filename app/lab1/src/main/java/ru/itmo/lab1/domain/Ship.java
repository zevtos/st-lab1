package ru.itmo.lab1.domain;

public final class Ship {
  private final String name;
  private final EngineType engine;
  private final Crew crew;

  private final BindingCause bindingCause;

  private final CrewState crewState;

  public Ship(String name, EngineType engine, Crew crew, BindingCause bindingCause, CrewState crewState) {
    if (name == null || name.isBlank()) throw new IllegalArgumentException("name must be non-empty");
    if (engine == null) throw new IllegalArgumentException("engine must not be null");
    if (crew == null) throw new IllegalArgumentException("crew must not be null");
    if (bindingCause == null) throw new IllegalArgumentException("bindingCause must not be null");
    if (crewState == null) throw new IllegalArgumentException("crewState must not be null");

    if (crewState.bindingCause() != bindingCause) {
      throw new IllegalArgumentException("crewState.bindingCause must match ship.bindingCause");
    }

    this.name = name;
    this.engine = engine;
    this.crew = crew;
    this.bindingCause = bindingCause;
    this.crewState = crewState;
  }

  public String name() { return name; }
  public EngineType engine() { return engine; }
  public Crew crew() { return crew; }
  public BindingCause bindingCause() { return bindingCause; }
  public CrewState crewState() { return crewState; }
}
