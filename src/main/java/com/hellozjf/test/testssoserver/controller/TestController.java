package com.hellozjf.test.testssoserver.controller;

import com.hellozjf.test.testssoserver.dataobject.Test;
import com.hellozjf.test.testssoserver.repository.TestRepository;
import com.hellozjf.test.testssoserver.util.ResultUtils;
import com.hellozjf.test.testssoserver.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jingfeng Zhou
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/get/")
    public ResultVO get() {
        return ResultUtils.success(testRepository.findAll());
    }

    @GetMapping("/get/{id}")
    public ResultVO get(@PathVariable("id") String id) {
        return ResultUtils.success(testRepository.findById(id).orElse(null));
    }

    @GetMapping("/getByName")
    public ResultVO getByName(String name) {
        return ResultUtils.success(testRepository.findByName(name));
    }

    @GetMapping("/getByAge")
    public ResultVO getByAge(Integer age) {
        return ResultUtils.success(testRepository.findByAge(age));
    }

    @GetMapping("/getByScore")
    public ResultVO getByScore(Double score) {
        return ResultUtils.success(testRepository.findByScore(score));
    }

    @PostMapping("/post/")
    public ResultVO post(String name, Integer age, Double score) {
        Test test = new Test();
        test.setName(name);
        test.setAge(age);
        test.setScore(score);
        test = testRepository.save(test);
        return ResultUtils.success(test);
    }
}
