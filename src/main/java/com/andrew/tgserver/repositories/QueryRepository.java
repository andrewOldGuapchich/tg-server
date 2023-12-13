package com.andrew.tgserver.repositories;

import com.andrew.tgserver.entities.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query, Integer> {
    Query getQueryByRusName(String rusName);
}
