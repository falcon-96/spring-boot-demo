package com.self.probe.service;

import com.self.probe.model.DroidList;
import com.self.probe.model.Droid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private DroidList droidList;

    public ResponseEntity<?> createNewDroid(String name) {
        droidList.getDroidList().add(new Droid(name));

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Added: [" + name + "]");
    }
    public ResponseEntity<DroidList> getAllDroids()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(droidList);
    }
}
