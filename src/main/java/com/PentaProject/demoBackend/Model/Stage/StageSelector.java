package com.PentaProject.demoBackend.Model.Stage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StageSelector {
    private List<Stage> stages;

    @Override
    public String toString() {
        return "StageSelector{" +
                "stages=" + stages +
                '}';
    }
}
