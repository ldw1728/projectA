package com.wooklee.project.test.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.wooklee.project.mappers.TestMapper;
import com.wooklee.project.model.TestModel;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
@WebAppConfiguration
public class TestMapperTest {
	
	@Autowired
	private TestMapper testMapper;
	
	private static TestModel testModel;
	
	@Rule public TestName testName = new TestName();
	
	@Before
	public void beforeTest() {
		log.info(" ");
		log.info("+++++++++++++++++ START	[ " + testName.getMethodName() + " ] ++++++++++++++++++++");
	}
	
	@After
	public void afterTest() {
		log.info("+++++++++++++++++ END		[ " + testName.getMethodName() + " ] ++++++++++++++++++++");
		log.info(" ");
	}
	
	@BeforeClass
	public static void setUp() {
		log.info("==================Test Start===================");
		testModel = TestModel.builder().str("wooklee").integer(2616).build();
	}
	
	@AfterClass
	public static void testEnd() {
		log.info("==================Test Complete===================");
	}
	
	@Test
	public void selectListTest() {
		List<TestModel> testModelList = testMapper.selectTestList();
		
		assertThat(testModelList, is(notNullValue()));
		assertTrue(testModelList.size() >= 0);
		
		log.info("List Size :    " + testModelList.size());
	}
	
	@Test
	public void selectTest() {
		TestModel temp = testMapper.selectTestOne(1L);
		
		log.info(temp.toString());
		
		assertNotNull(temp);
	}
	
	
	@Test
	@Transactional
	public void insertTest() {
		
		int result = testMapper.insertTestModel(testModel);
		
		log.info(testModel.toString() + "		result : " + result);
		
		assertTrue(result > 0);
	}
	
	@Test
	@Transactional
	public void updateTest() {
		TestModel temp = testMapper.selectTestOne(1L);
		
		log.info(temp.toString() + "		===== before update =====");
		
		temp.setStr("김동욱");
		temp.setInteger(10000);
		int result = testMapper.updateTestModel(temp);
		
		log.info(temp.toString() + "		result : " + result + "		===== after update =====");
		
		assertTrue(result > 0);
	}
	
	@Test
	@Transactional
	public void deleteTest() {
		Long id = 1L;
		int result = testMapper.deleteTestModel(id);
		
		assertTrue(result > 0);
		
		TestModel temp = testMapper.selectTestOne(id);
		
		assertThat(temp, is(nullValue()));
	}
	
	
	@Test(expected=NullPointerException.class)
	@Transactional
	public void updateTest_idIsNull() {
		
		TestModel temp = testMapper.selectTestOne(null);
		temp.getId();
		//assertTrue(temp == null);
	}

}
