package com.example.fileoperation;

import com.deepoove.poi.data.Charts;
import com.deepoove.poi.data.TableRenderData;
import com.example.fileoperation.word.WordGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		// 时间需要传字符串
		data.put("startTime", "2022-09-10");
		data.put("endTime", "2022-09-16");
		// 表格
		String[] tableTitleArr = new String[] {"序号", "组件漏洞", "数量"};
		List<String[]> tableDataList = new ArrayList<>();
		tableDataList.add(new String[] {"1", "代码注入", "20"});
		tableDataList.add(new String[] {"2", "命令注入", "10"});
		TableRenderData tableRenderData = WordGenerator.generateTableData(
				tableTitleArr, tableDataList);
		data.put("testTable1", tableRenderData);
		// 折线图 纵坐标单位: 模板中设置纵坐标标题 -- 图表元素设置
		data.put("testChart1", Charts
				.ofMultiSeries("测试折线图01", new String[] {"2020-09-18", "2020-09-19", "2020-09-20", "2020-09-21", "2020-09-22"})
				.addSeries("扫描数", new Integer[] { 10, 15, 7, 20, 12 })
				.addSeries("命中数", new Integer[] { 8, 10, 7, 10, 0 })
				.create());
		// 条件判断

		WordGenerator.generate(template, data, out);
	}

}
