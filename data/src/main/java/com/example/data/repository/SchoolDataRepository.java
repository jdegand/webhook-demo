package com.example.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.data.model.SchoolData;

public interface SchoolDataRepository extends JpaRepository<SchoolData, Integer> {

}
