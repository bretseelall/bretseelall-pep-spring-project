package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("FROM Account WHERE username = :username")
    Account userExist(@Param ("username") String name);

    @Query("SELECT password FROM Account WHERE username = :username")
    String userPassCorrect(@Param("username") String name);
}
