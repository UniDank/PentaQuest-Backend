package com.PentaProject.demoBackend.Services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import com.PentaProject.demoBackend.Model.Hero;
import com.PentaProject.demoBackend.Model.Item;
import com.PentaProject.demoBackend.Model.Party;
import com.PentaProject.demoBackend.Repositories.PartyRepository;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe Service per la gestione della logica relativa agli oggetti di tipo
 * party
 * 
 * @version 1.0
 * @author Marco
 */

@Service
@AllArgsConstructor
public class PartyService {
    private PartyRepository rep;
    // private static int value;

    /**
     * Metodo per inserire un oggetto party all'interno del db.
     *
     * @param party non deve essere null
     */
    public void insertParty(@NonNull Party party) {
        rep.insert(party);
    }

    /**
     * Permette di ottenere tutto il party
     * 
     * @return Party
     * @throws NoSuchElementException se non trova il party nel db
     */

    public Party getParty() throws NoSuchElementException {
        return rep.findAll().stream().findFirst().orElseThrow(NoSuchElementException::new);
    }

    /**
     * Permette di generare un oggetto party
     * 
     * @param bag      lista di Item
     * @param heroList lista di Hero
     */
    public Party generateParty(int id_stage, List<Hero> heroList, List<Item> bag) {
        return new Party(id_stage, heroList, bag);
    }

    public void deleteParty() {
        rep.deleteAll();
    }
}
