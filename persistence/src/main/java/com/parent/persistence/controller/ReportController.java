package com.parent.persistence.controller;

import com.parent.persistence.model.presentation.Raport;
import com.parent.persistence.services.RaportService;
import com.parent.persistence.services.implementation.RaportServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
public class ReportController {
//    @Autowired
    RaportService raportService;

    public ReportController(RaportServiceImpl raportService) {
        this.raportService = raportService;
    }

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
