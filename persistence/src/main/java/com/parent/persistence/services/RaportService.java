package com.parent.persistence.services;

import com.parent.persistence.constants.TypeEnum;
import com.parent.persistence.model.*;
import com.parent.persistence.model.presentation.Raport;
import com.parent.persistence.model.presentation.RaportPresentation;
import com.parent.persistence.model.presentation.TransactionDetails;
import com.parent.persistence.repository.TransactionMemberRepository;
import com.parent.persistence.repository.TransactionRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RaportService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMemberRepository transactionMemberRepository;

    @Autowired
    ModelMapper modelMapper;

    public Raport createRaport(String cnp) {

        List<Transaction> lt = transactionRepository.findByCnp(cnp);


        HashMap<String, RaportPresentation> presentationHashMap = new HashMap<>();
        if(lt.size() >0){
            //instantiem Numele, CNP-ul si IBAN-ul expeditorului
            Raport raport = new Raport(lt.get(0).getPayer().getName(),lt.get(0).getPayer().getCnp(),lt.get(0).getPayer().getIban(),new ArrayList<>());


            for(Transaction t: lt){
//                if(raport.getIban()==null && t.getPayer().getIban()!=null){
//                    raport.setIban(t.getPayer().getIban());
//                }

//                pentru fiecare tip de tranzanctie calculam suma totala si numarul tranzactiilor si mapam informatiile
//                intr-un hash, grupate dupa tip
                if(presentationHashMap.get(t.getType()) == null){
                    presentationHashMap.put(t.getType(),new RaportPresentation(t.getType(),1,t.getAmount(),new ArrayList<>()));
                }else{
                    RaportPresentation rpCurrent = presentationHashMap.get(t.getType());
                    rpCurrent.setCount(rpCurrent.getCount()+1);
                    rpCurrent.setTotal(rpCurrent.getTotal().add(t.getAmount()));
                }
            }

//            grupam detaliile tranzactiilor intr-un hash in functie de tip
            Map<String, List<TransactionDetails>> transactionDetailsList = lt.stream()
                    .map(p -> new TransactionDetails(p.getType(),p.getAmount(), p.getDescription(), p.getPayee().getName(),p.getPayee().getCnp(),p.getPayee().getIban()))
                    .collect(Collectors.toList())
                    .stream()
                    .collect(Collectors.groupingBy(TransactionDetails::getType));

//                pentru fiecare tip de tranzactie formam obiectul raport
            for (TypeEnum t : TypeEnum.class.getEnumConstants()){
                if(presentationHashMap.get(t.toString()) != null){
                    RaportPresentation raportPresentation = new RaportPresentation(
                            presentationHashMap.get(t.toString()).getType(),
                            presentationHashMap.get(t.toString()).getCount(),
                            presentationHashMap.get(t.toString()).getTotal(),
                            transactionDetailsList.get(t.toString())
                    );
                    raportPresentation.setSumaTotala(raportPresentation.getTotal().toString() + " RON");
                    raportPresentation.setCountString(raportPresentation.getCount() + " tranzactii");
                    raport.getTransactions().add(raportPresentation);
                }else{
                    RaportPresentation raportPresentation = new RaportPresentation();
                    raportPresentation.setType(t.toString());
                    raportPresentation.setCountString("Fara Tranzactii");
                    raport.getTransactions().add(raportPresentation);

                }
            }



            return raport;
        }
        return null;
    }
}
