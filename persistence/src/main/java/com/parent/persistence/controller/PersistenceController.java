package com.parent.persistence.controller;

import com.parent.persistence.model.TransactionMember;
import com.parent.persistence.services.TransactionMemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class PersistenceController {

    TransactionMemberService transactionMemberService;

    public PersistenceController(TransactionMemberService transactionMemberService) {
        this.transactionMemberService = transactionMemberService;
    }

    @GetMapping(value="/{cnp}")
    public ResponseEntity<List<TransactionMember>> getTransactionMemberById(@PathVariable("cnp") String cnp){
        List<TransactionMember> entityList =  transactionMemberService.findByCnp(cnp);
        return new ResponseEntity<List<TransactionMember>>(entityList, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<TransactionMember>> getAllTransactionMember(){
        List<TransactionMember> entityList =  transactionMemberService.findAll();
        return new ResponseEntity<List<TransactionMember>>(entityList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value="/pagination")
    public ResponseEntity<List<TransactionMember>> getTransactionMembePagination(@RequestParam int pageNo){
        List<TransactionMember> entityList =  transactionMemberService.findAll(pageNo-1);
        return new ResponseEntity<List<TransactionMember>>(entityList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransactionMember> createOrUpdateTransactionMember(@RequestBody TransactionMember transactionMember){
        TransactionMember saved = transactionMemberService.save(transactionMember);
        return new ResponseEntity<TransactionMember>(saved, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<TransactionMember> updateTransactionMember(@PathVariable("id") Long clientId,
                                                                     @RequestBody TransactionMember transactionMember) {
        TransactionMember updated = transactionMemberService.update(clientId, transactionMember);
        return new ResponseEntity<TransactionMember>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteTransactionMember(@PathVariable("id") Long id){
        transactionMemberService.deleteById(id);
        return ResponseEntity.ok("Object with id : "+id+" has been deleted");
    }


}
