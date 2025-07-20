package com.PentaProject.demoBackend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Ability;
import com.PentaProject.demoBackend.Services.AbilityService;

/**
 * Non in uso in questa versione
 */
@Controller
@AllArgsConstructor
public class AbilityController {

    private AbilityService abilityService;

    public ApiResponse<Ability> getAbility(int id) {
        // TODO: Gestione degli errori
        return new ApiResponse<>(HttpStatus.OK, abilityService.getAbility(id));
    }

    public ApiResponse<?> setAbility(Ability ability) {
        // TODO: gestione degli errori
        abilityService.insertAbility(ability);
        return new ApiResponse<>(HttpStatus.OK, "");
    }
}
