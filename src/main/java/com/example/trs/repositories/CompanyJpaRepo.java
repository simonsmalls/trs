package com.example.trs.repositories;

import com.example.trs.model.Activity;
import com.example.trs.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyJpaRepo extends JpaRepository<Company, Integer> {

    @Query(value = "select * from companies where companyname =:name", nativeQuery = true)
    Optional<Company> findCompanyByCompanyName(@Param("name") String companyName);

    Company findCompanyByIdAndCompanyName(int id,String name);
    Company findCompanyById(int id);


}
