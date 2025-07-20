package com.PentaProject.demoBackend.Services;

import org.springframework.stereotype.Service;

import com.PentaProject.demoBackend.Model.ClassType;
import com.PentaProject.demoBackend.Model.Hero;
import com.PentaProject.demoBackend.Repositories.HeroRepository;

/**
 * Classe Service per la gestione della logica relativa agli oggetti di tipo
 * hero
 * Non in uso in questa versione
 * 
 * @version 1.0
 * @author Marco
 */

@Service
public class HeroService {
    private HeroRepository rep;

    public void insertHero(Hero hero) {
        rep.insert(hero);
    }

    public Hero getHero(int id) {
        return rep.findById(id).orElse(null);
    }

    public Hero generateHero(String name, Integer attack, Integer defence, Integer health, Integer mana,
            Integer agility, Integer APs, Integer range, ClassType category) {
        return new Hero(name, attack, defence, health, mana, agility, APs, range, category);
    }

    public void insertAbility(Hero hero) {

    }
}
