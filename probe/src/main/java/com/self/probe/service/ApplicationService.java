package com.self.probe.service;

import com.self.probe.model.DroidList;
import com.self.probe.model.Droid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ApplicationService {

    private final DroidList droidList;

    public ApplicationService(DroidList droidList) {
        this.droidList = droidList;
    }

    public ResponseEntity<String> createNewDroid(String name) {
        droidList.getDroidList().add(new Droid(name));

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Added: [" + name + "]");
    }

    public ResponseEntity<DroidList> getAllDroids() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(droidList);
    }

    public ResponseEntity<String> updateName(String existingName, String newName) {
        int numberOfItemsChanged = droidList.changeData(existingName, newName);
        if (numberOfItemsChanged > 0) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(numberOfItemsChanged + " items updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No items found with " + existingName);
        }
    }

    public ResponseEntity<String> deleteDroid(String name) {
        List<Integer> droids = droidList.indexOf(name);
        if (droids.size() > 0) {
            droidList.removeAll(name);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Removed " + droids.size() + " droid/s.");
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("No Droids with name:[" + name + "]");
        }

    }
}
