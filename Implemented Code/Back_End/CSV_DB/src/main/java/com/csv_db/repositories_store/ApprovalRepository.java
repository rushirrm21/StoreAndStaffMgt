package com.csv_db.repositories_store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csv_db.model_store.ForApprovalStore;

public interface ApprovalRepository extends JpaRepository<ForApprovalStore, String> {
}
