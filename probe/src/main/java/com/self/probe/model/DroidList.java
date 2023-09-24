package com.self.probe.model;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroidList {
    private List<Droid> droidList;

    @PostConstruct
    private void init()
    {
        droidList = new LinkedList<>();
    }
}
