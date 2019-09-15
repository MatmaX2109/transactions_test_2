package com.parent.persistence.controller;

import com.parent.persistence.model.presentation.Raport;
import com.parent.persistence.services.RaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
public class PersistenceController {
    @Autowired
    RaportService raportService;

    /**
     * Controller pentru creare rapoarte
     * @param cnp
     * @return
     */
    @RequestMapping(value="showReport", method = RequestMethod.GET)
    public Object sendTransaction(@RequestParam String cnp) {

//    @GetMapping(value = "showReport/{cnp}")
//    public ResponseEntity<Map<String, Object>> sendTransaction(@PathVariable String cnp){


        Raport raport = raportService.createRaport(cnp);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(cnp, "Nu au fost gasite inregistrari");
        return raport != null ? raport : body;
    }
}
