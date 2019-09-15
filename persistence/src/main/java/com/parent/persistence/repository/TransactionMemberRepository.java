package com.parent.persistence.repository;

import com.parent.persistence.model.TransactionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMemberRepository extends JpaRepository<TransactionMember,Long> {

    public TransactionMember findByCnp(String cnp);
}
