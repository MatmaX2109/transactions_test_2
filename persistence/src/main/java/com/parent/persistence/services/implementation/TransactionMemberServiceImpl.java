package com.parent.persistence.services.implementation;

import com.parent.persistence.model.TransactionMember;
import com.parent.persistence.repository.TransactionMemberRepository;
import com.parent.persistence.services.TransactionMemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionMemberServiceImpl implements TransactionMemberService {

    private TransactionMemberRepository transactionMemberRepository;

    public TransactionMemberServiceImpl(TransactionMemberRepository transactionMemberRepository) {
        this.transactionMemberRepository = transactionMemberRepository;
    }

    private Optional<TransactionMember> findById(Long id) {
        return transactionMemberRepository.findById(id);
    }

    @Override
    public List<TransactionMember> findByCnp(final String cnp){
        return transactionMemberRepository.findByCnp(cnp);
    }

    @Override
    public List<TransactionMember> findAll() {
        return transactionMemberRepository.findAll();
    }

    @Override
    public List<TransactionMember> findAll(final int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 3);
        return transactionMemberRepository.findAll(pageable).getContent();
    }

    @Override
    public TransactionMember save(final TransactionMember transactionMember) {
        return transactionMemberRepository.save(transactionMember);
    }

    @Override
    public TransactionMember update(final Long id, final TransactionMember transactionMember) {
        TransactionMember toBeUpdated = findById(id).get();
        toBeUpdated.setIban(transactionMember.getIban());
        toBeUpdated.setCnp(transactionMember.getCnp());
        toBeUpdated.setName(transactionMember.getName());
        return save(toBeUpdated);
    }

    @Override
    public void deleteById(Long id) {
        transactionMemberRepository.deleteById(id);
    }
}
