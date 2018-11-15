package com.hellozjf.test.testssoserver.repository;

import com.hellozjf.test.testssoserver.dataobject.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
public interface TestRepository extends JpaRepository<Test, String> {
    List<Test> findByName(String name);
    List<Test> findByAge(Integer age);
    List<Test> findByScore(Double score);
}
