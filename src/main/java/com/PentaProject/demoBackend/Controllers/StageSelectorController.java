package com.PentaProject.demoBackend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Party;
import com.PentaProject.demoBackend.Model.Save;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * RestFull controller, aka Main controller
 * 
 * @RequestMapping /api/v1 ;Path controllata dal controller
 *
 * @author Marco
 * @version 1.0
 *
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin
public class StageSelectorController {
    private PartyController partyController;
    private EnemyController enemyController;
    private SaveController saveController;

    /**
     * Metodo per ottenere il party rispetto id dello stage.
     * Enumerazione [0,n-1] per gli stage
     */
    @Operation(summary = "Get current party", description = "Retrieves the current party configuration for the game stage")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Party retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Party not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/party")
    public ApiResponse<?> getCurrentParty() {
        return partyController.getParty();
    }

    /**
     * Metodo per ottenere i nemici rispetto id dello stage
     * Enumerazione [0,n-1] per gli stage
     * 
     * @param id id dello stage che viene preso dalla path del uri
     */
    @Operation(summary = "Get enemies by stage ID", description = "Retrieves enemies for a specific stage. Stage enumeration ranges from [0, n-1]")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Enemies retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid stage ID"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Stage not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}/enemies")
    public ApiResponse<?> getEnemy(@PathVariable int id) {
        return enemyController.getEnemies(id);
    }

    /**
     * Metodo per inserire il party nel db
     * 
     * @param party json rapresentante oggetto Party
     */
    @Operation(summary = "Set current party", description = "Saves the party configuration to the database")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Party saved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Party created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid party data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/party")
    // @RequestMapping(method = RequestMethod.POST, consumes =
    // MediaType.APPLICATION_JSON_VALUE, path = "/party")
    public ApiResponse<?> setCurrentParty(@RequestBody Party party) {
        return partyController.setParty(party);
    }

    /**
     * Metodo per ottenere il salvataggio dal db
     */
    @Operation(summary = "Get save data", description = "Retrieves the current game save data from the database")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Save data retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Save data not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/select")
    public ApiResponse<?> getSave() {
        return saveController.getSave();
    }

    /**
     * Metodo per inserire il salvataggio nel db
     * 
     * @param save json rapresentante oggetto Save
     */
    @Operation(summary = "Set save data", description = "Saves the current game state to the database")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Save data updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Save data created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid save data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/select")
    public ApiResponse<?> setSave(@RequestBody Save save) {
        return saveController.setSave(save);
    }

}
