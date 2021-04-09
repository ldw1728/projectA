package com.wooklee.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooklee.project.mappers.TestMapper;
import com.wooklee.project.model.TestModel;

@Service
public class TestService {
	
	@Autowired
	private TestMapper testMapper;
	
	public List<TestModel> selectTestList(){
		return testMapper.selectTestList();
	}
	
	public TestModel selectTestOne(Long id) {
		return testMapper.selectTestOne(id);
	}
	
	public int insertTestModel(TestModel testModel) {
		return testMapper.insertTestModel(testModel);
	}

	public int updateTestModel(TestModel testModel) {
		return testMapper.updateTestModel(testModel);
	}
	
	public int deleteTestModel(Long id) {
		return testMapper.deleteTestModel(id);
	}

}
