package com.self.probe.model;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroidList {
    private List<Droid> droidList;

    public boolean contains(String name) {
        return this.getDroidList().stream()
                .anyMatch(droid -> droid.getName().equals(name));
    }

    public List<Integer> indexOf(String existingName) {
        List<Integer> list = new LinkedList<>();
        List<Droid> tempDroidList = this.getDroidList();
        for (int i = 0; i < this.getDroidList().size(); i++) {
            if (tempDroidList.get(i).getName().equals(existingName)) {
                list.add(i);
            }
        }

        return list;
    }

    public int changeData(String existingName, String newName) {
        List<Integer> indexOfExisting = indexOf(existingName);

        if (indexOfExisting != null) {
            for (int i : indexOfExisting) {
                this.getDroidList().set(i, new Droid(newName));
            }

        } else {
            return 0;
        }
        return indexOfExisting.size();
    }


    public boolean removeAll(String name) {
       return this.getDroidList().removeIf(droid -> droid.getName().equals(name));

    }

    @PostConstruct
    private void init() {
        droidList = new LinkedList<>();
    }
}
