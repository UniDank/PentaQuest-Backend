package com.PentaProject.demoBackend.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Hero;
import com.PentaProject.demoBackend.Services.HeroService;

import lombok.AllArgsConstructor;

/**
 * Non in uso in questa versione
 */
@Controller
@AllArgsConstructor
public class HeroController {

    private HeroService heroService;

    public ApiResponse<Hero> getHero(int id) {
        // TODO: Gestione degli errori
        return new ApiResponse<>(HttpStatus.OK, heroService.getHero(id));
    }

    public ApiResponse<Hero> setHero(Hero hero) {
        // TODO: gestione degli errori
        heroService.insertAbility(hero);
        return new ApiResponse<>(HttpStatus.OK, "");
    }
}
