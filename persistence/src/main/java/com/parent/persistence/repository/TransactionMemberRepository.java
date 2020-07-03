package com.parent.persistence.repository;

import com.parent.persistence.model.TransactionMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionMemberRepository extends JpaRepository<TransactionMember,Long> {

    Optional<TransactionMember> findById(final Long id);

    List<TransactionMember> findByCnp(final String cnp);

    List<TransactionMember> findAll();

    Page<TransactionMember> findAll(final Pageable pageable);

    TransactionMember save(final TransactionMember transactionMember);

    void deleteById(final Long id);
}
