package ru.itmo.lab1.domain;

public record CrewState(CrewFeeling feeling,
                        BindingCause bindingCause,
                        boolean awareOfCause,
                        String rationale) {

  public CrewState {
    if (feeling == null) throw new IllegalArgumentException("feeling must not be null");
    if (bindingCause == null) throw new IllegalArgumentException("bindingCause must not be null");

    if (rationale != null && rationale.isBlank()) {
      throw new IllegalArgumentException("rationale must be non-blank if provided");
    }

    if (bindingCause == BindingCause.PHYSICAL_PRINCIPLE && awareOfCause && feeling != CrewFeeling.UNEASY) {
      throw new IllegalArgumentException("If aware of PHYSICAL_PRINCIPLE, crew must feel UNEASY (scene invariant)");
    }
  }
}
