package com.PentaProject.demoBackend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.PentaProject.demoBackend.Controllers.FormatResponse.ApiResponse;
import com.PentaProject.demoBackend.Model.Party;
import com.PentaProject.demoBackend.Services.PartyService;

import java.util.NoSuchElementException;

/* {
        "Party": {
            "Members": [
            {
                //Hero
            }],
            "Bag": [
            {
               //Item
            }],
         "currentProgression": 0,
     }
 */

/**
 * Classe controller per gestire le richieste dell'oggetto party
 * 
 * @version 1.0
 * @author Marco
 */

@Controller
@AllArgsConstructor
public class PartyController {
    private PartyService partyService;

    public ApiResponse<Party> getParty() {
        try {
            return new ApiResponse<>(HttpStatus.OK, partyService.getParty());
        } catch (NoSuchElementException ex) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Non esiste il party");
        }
    }

    public ApiResponse<?> setParty(@RequestBody Party party) {
        if (party == null)
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Body invalido");
        partyService.deleteParty();
        partyService.insertParty(party);
        return new ApiResponse<>(HttpStatus.OK, "");
    }

}
