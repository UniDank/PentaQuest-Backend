package com.PentaProject.demoBackend.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.PentaProject.demoBackend.Model.Item;
import com.PentaProject.demoBackend.Repositories.ItemRepository;

/**
 * Classe Service per la gestione della logica relativa agli oggetti di tipo
 * item
 * Non in uso in questa versione
 * 
 * @version 1.0
 * @author Marco
 */

@Service
@AllArgsConstructor
public class ItemService {
    private ItemRepository rep;
    // private static int value;

    public void insertItem(Item item) {
        rep.insert(item);
    }

    public Item getItem(int id) {
        return rep.findById(id).orElse(null);
    }

    public Item generateItem(String name, String type, Integer value, Integer quantity) {
        return new Item(name, type, value, quantity);
    }

    public void insertAbility(Item item) {
    }
}
