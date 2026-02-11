package ru.itmo.lab1.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DomainModelTest {

  private static Crew crewOf4() {
    return new Crew(List.of(
        new Person("p1"),
        new Person("p2"),
        new Person("p3"),
        new Person("p4")
    ));
  }

  @Test
  void storyFixture_fromText_isValid_andConsistent() {
    Crew crew = crewOf4();

    BindingCause cause = BindingCause.PHYSICAL_PRINCIPLE;

    CrewState state = new CrewState(
        CrewFeeling.UNEASY,
        cause,
        true,
        "Они осознают, что вместе по странному физическому принципу"
    );

    Ship ship = new Ship(
        "Золотое Сердце",
        EngineType.PHOTON,
        crew,
        cause,
        state
    );

    assertEquals("Золотое Сердце", ship.name());
    assertEquals(EngineType.PHOTON, ship.engine());
    assertEquals(4, ship.crew().members().size());
    assertEquals(BindingCause.PHYSICAL_PRINCIPLE, ship.bindingCause());
    assertTrue(ship.crewState().awareOfCause());
    assertEquals(CrewFeeling.UNEASY, ship.crewState().feeling());
    assertEquals(ship.bindingCause(), ship.crewState().bindingCause());
  }

  @Test
  void crewSizeMustBeExactlyFour() {
    assertThrows(IllegalArgumentException.class, () -> new Crew(List.of(
        new Person("p1"), new Person("p2"), new Person("p3")
    )));
    assertThrows(IllegalArgumentException.class, () -> new Crew(List.of(
        new Person("p1"), new Person("p2"), new Person("p3"), new Person("p4"), new Person("p5")
    )));
  }

  @Test
  void crewMembersMustBeUnique() {
    Person p = new Person("p1");
    assertThrows(IllegalArgumentException.class, () -> new Crew(List.of(p, p, new Person("p3"), new Person("p4"))));
  }

  @Test
  void personIdMustBeNonEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new Person(""));
  }

  @Test
  void shipNameMustBeNonEmpty() {
    Crew crew = crewOf4();
    CrewState state = new CrewState(CrewFeeling.UNEASY, BindingCause.PHYSICAL_PRINCIPLE, true, null);
    assertThrows(IllegalArgumentException.class, () ->
        new Ship(" ", EngineType.PHOTON, crew, BindingCause.PHYSICAL_PRINCIPLE, state)
    );
  }

  @Test
  void crewStateInvariant_physicalPrincipleAwareImpliesUneasy() {
    assertThrows(IllegalArgumentException.class, () ->
        new CrewState(CrewFeeling.CALM, BindingCause.PHYSICAL_PRINCIPLE, true, "противоречит сцене")
    );
  }

  @Test
  void shipInvariant_bindingCauseMustMatchCrewStateCause() {
    Crew crew = crewOf4();
    CrewState state = new CrewState(CrewFeeling.CALM, BindingCause.COINCIDENCE, true, null);

    assertThrows(IllegalArgumentException.class, () ->
        new Ship("Золотое Сердце", EngineType.PHOTON, crew, BindingCause.PHYSICAL_PRINCIPLE, state)
    );
  }
}
