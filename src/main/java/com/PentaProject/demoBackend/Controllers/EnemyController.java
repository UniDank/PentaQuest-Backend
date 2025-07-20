package com.PentaProject.demoBackend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Enemy;
import com.PentaProject.demoBackend.Services.EnemyService;

import java.io.IOException;
import java.util.List;

//todo: usare i nomi come id univoci!
@Controller
@AllArgsConstructor
public class EnemyController {
    private EnemyService enemyService;

    /**
     * Metodi per restituire i nemici relativi allo stage selezionato.
     * Restituisce una lista di nemici, di cui alcuni copiati!
     * 
     * @param id id dello stage
     *
     */
    public ApiResponse<List<Enemy>> getEnemies(Integer id) {
        try {
            var data = enemyService.getEnemiesFromStage(id);
            return new ApiResponse<>(HttpStatus.OK, data);
        } catch (IOException ex) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "File non trovato oppure problemi con esso");
        }
    }

}
