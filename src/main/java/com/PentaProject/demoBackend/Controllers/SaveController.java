package com.PentaProject.demoBackend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Save;
import com.PentaProject.demoBackend.Services.SaveService;

import java.util.NoSuchElementException;

/**
 * Classe controller per gestire le richieste dell'oggetto save
 * 
 * @version 1.0
 * @author Marco
 */
@Controller
@AllArgsConstructor
public class SaveController {
    private SaveService saveService;

    public ApiResponse<Save> getSave() {
        try {
            return new ApiResponse<>(HttpStatus.OK, saveService.getSave());
        } catch (NoSuchElementException ex) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Salvataggio non trovato!");
        }

    }

    public ApiResponse<?> setSave(Save save) {
        if (save == null)
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Body non valido");
        saveService.removeSave();
        saveService.insertSave(save);
        return new ApiResponse<>(HttpStatus.OK, "");
    }

}
