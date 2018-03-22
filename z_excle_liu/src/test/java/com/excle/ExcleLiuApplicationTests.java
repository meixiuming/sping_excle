package com.excle;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcleLiuApplicationTests {
	private Logger logger = Logger.getLogger(getClass());

	@Test
	public void contextLoads() {
		
	}

	@Test
	public void test() throws Exception {
		logger.info("输出info");
		logger.debug("输出debug");
		logger.error("输出error");
	}

}
