package com.parent.persistence.services;

import com.parent.persistence.model.TransactionMember;

import java.util.List;

public interface TransactionMemberService {

    List<TransactionMember> findByCnp(final String cnp);

    List<TransactionMember> findAll();

    List<TransactionMember> findAll(final int pageNumber);

    TransactionMember save(final TransactionMember transactionMember);

    TransactionMember update(final Long id, final TransactionMember transactionMember);

    void deleteById(final Long id);

}
