package com.sumerge.test;

import com.sumerge.test.topic.TopicControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = {TopicControllerTest.class})

class TestApplicationTests {

	@Test
	void contextLoads() {
	}

}
