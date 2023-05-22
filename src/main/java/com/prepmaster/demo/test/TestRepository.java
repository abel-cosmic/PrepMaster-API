package com.prepmaster.demo.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
//    @Query("SELECT t FROM Test t WHERE t.id = :id")
//    Optional<Test> findById(@Param("id") Long id);
//
//    @Query("SELECT t FROM Test t JOIN t.bundle b WHERE t.id = :id AND b.id = :bundleId")
//    Optional<Test> findByBundleId(@Param("bundleId") Long bundleId, @Param("id") Long id);
//
//    @Query("SELECT t FROM Test t JOIN t.student s WHERE s.id = :studentId AND t.bundle.id = :bundleId")
//    List<Test> findByStudentIdAndBundleId(@Param("studentId") Long studentId, @Param("bundleId") Long bundleId);
//
//    @Query("SELECT t FROM Test t JOIN t.student s WHERE s.id = :studentId")
//    List<Test> findByStudentId(@Param("studentId") Long studentId);
//
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Test t WHERE t.id = :id")
//    void deleteById(@Param("id") Long id);
}
