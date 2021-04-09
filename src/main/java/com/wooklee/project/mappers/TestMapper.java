package com.wooklee.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wooklee.project.model.TestModel;

@Mapper
@Repository
public interface TestMapper {
	
	public List<TestModel> selectTestList();
	
	public TestModel selectTestOne(Long id);
	
	public int insertTestModel(TestModel testModel);

	public int updateTestModel(TestModel testModel);
	
	public int deleteTestModel(Long id);

}
