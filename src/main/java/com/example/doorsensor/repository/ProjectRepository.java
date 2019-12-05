package com.example.doorsensor.repository;

import com.example.doorsensor.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>,
        JpaSpecificationExecutor<Project> {

    @Query(value = "select * from project p where p.id = ?1", nativeQuery = true)
    Project findOneById(long id);

    @Query(value = "select * from project p where p.pk_name = ?1", nativeQuery = true)
    Project findOneByName(String name);
}
