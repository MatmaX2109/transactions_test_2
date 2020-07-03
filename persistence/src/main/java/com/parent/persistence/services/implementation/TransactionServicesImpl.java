package com.parent.persistence.services.implementation;

import com.parent.persistence.model.Transaction;
import com.parent.persistence.model.TransactionMember;
import com.parent.persistence.repository.TransactionMemberRepository;
import com.parent.persistence.repository.TransactionRepository;
import com.parent.persistence.services.TransactionServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServicesImpl implements TransactionServices {

//    @Autowired
    TransactionRepository transactionRepository;

//    @Autowired
    TransactionMemberRepository transactionMemberRepository;

    public TransactionServicesImpl(TransactionRepository transactionRepository, TransactionMemberRepository transactionMemberRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMemberRepository = transactionMemberRepository;
    }

    /**
     * Salveaza in DB tranzactia tinand cont ca pentru TransactionMember sa nu existe duplicati
     * @param transaction
     */
    public void saveTransaction(Transaction transaction){

        List<TransactionMember> payers = transactionMemberRepository.findByCnp(transaction.getPayer().getCnp());
        List<TransactionMember> payees = transactionMemberRepository.findByCnp(transaction.getPayee().getCnp());

        if(payers.size()==1){
            if(payers.get(0).getIban()==null && transaction.getPayer().getIban()!=null){
                payers.get(0).setIban(transaction.getPayer().getIban());
            }
            transaction.setPayer(payers.get(0));
        }
//        else create custom error for multiple records
        if(payees.size()==1){
            if(payees.get(0).getIban()==null && transaction.getPayee().getIban()!=null){
                payees.get(0).setIban(transaction.getPayee().getIban());
            }
            transaction.setPayee(payees.get(0));
        }
        transactionRepository.save(transaction);
    }
//        else create custom error for multiple records

}
