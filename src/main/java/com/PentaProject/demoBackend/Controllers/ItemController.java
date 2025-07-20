package com.PentaProject.demoBackend.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Item;
import com.PentaProject.demoBackend.Services.ItemService;

import lombok.AllArgsConstructor;

/**
 * Non in uso in questa versione
 */

@Controller
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    public ApiResponse<Item> getItem(int id) {
        // TODO: Gestione degli errori
        return new ApiResponse<>(HttpStatus.OK, itemService.getItem(id));
    }

    public ApiResponse<Item> setItem(Item item) {
        // TODO: gestione degli errori
        itemService.insertAbility(item);
        return new ApiResponse<>(HttpStatus.OK, "");
    }
}
