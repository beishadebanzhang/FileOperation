package com.example.fileoperation;

import com.example.fileoperation.word.WordGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FileOperationApplicationTests {

	@Test
	void contextLoads() throws Exception {
		String template = "src/main/resources/template/word模板.docx";
		String out = "src/main/resources/template/word报告压缩包.zip";
		Map<String, Object> data = new HashMap<>();
		// 设置段落缩进为0可保证不换行
		data.put("title", "模板报告标题");
		WordGenerator.generate(template, data, out);
	}

}
