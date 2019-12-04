package com.example.doorsensor.repository;

import com.example.doorsensor.domain.SingleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleRequestRepository extends JpaRepository<SingleRequest, Long>,
        JpaSpecificationExecutor<SingleRequest> {
}
