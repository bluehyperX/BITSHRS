package com.app.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.hotelmanagementsystem.entity.Tables;

public interface TableRepository extends JpaRepository<Tables, Long> {

}
