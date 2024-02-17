package com.self.probe.model;

import jakarta.validation.constraints.NotEmpty;


public record Droid(
        @NotEmpty(message = "name cannot be null")
        String name,

        @NotEmpty(message = "model cannot be null")
        String model
) {
}

