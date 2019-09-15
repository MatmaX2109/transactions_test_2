package com.parent.persistence.services;

import com.parent.persistence.model.Transaction;
import com.parent.persistence.model.TransactionMember;
import com.parent.persistence.repository.TransactionMemberRepository;
import com.parent.persistence.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersistenceServices {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMemberRepository transactionMemberRepository;

    /**
     * Salveaza in DB tranzactia tinand cont ca pentru TransactionMember sa nu existe duplicati
     * @param transaction
     */
    public void saveTransaction(Transaction transaction){

        TransactionMember payer = transactionMemberRepository.findByCnp(transaction.getPayer().getCnp());
        TransactionMember payee = transactionMemberRepository.findByCnp(transaction.getPayee().getCnp());

        if(payer!=null){
            if(payer.getIban()==null && transaction.getPayer().getIban()!=null){
                payer.setIban(transaction.getPayer().getIban());
            }
            transaction.setPayer(payer);
        }
        if(payee!=null){
            if(payee.getIban()==null && transaction.getPayee().getIban()!=null){
                payee.setIban(transaction.getPayee().getIban());
            }
            transaction.setPayee(payee);
        }
        transactionRepository.save(transaction);
    }

}
