package com.prepmaster.demo.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AdminRepository  extends JpaRepository<Admin,Long> {
    //Create update handled by save
    //Read
    @Query("SELECT a FROM Admin a WHERE a.id = :id")
    Optional<Admin> findById(@Param("id") Long id);

//    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.choices WHERE q.id = :questionId")
//    Question findQuestionWithChoices(@Param("questionId") Long questionId);
//    @Query("SELECT q, q.answerIndex FROM Question q LEFT JOIN FETCH q.choices WHERE q.id = :questionId")
//    Object[] findQuestionWithChoicesAndAnswerIndex(@Param("questionId") Long questionId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Admin a WHERE a.id = :id")
    int deleteAdminById(@Param("id") Long id);// before this is called all the orphans must be removed it won't be done automatically
}
