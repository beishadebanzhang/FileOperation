package com.example.fileoperation.word;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.policy.TOCRenderPolicy;
import com.example.fileoperation.common.Reader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * world根据模板生成内容
 */
@Data
public class WordGenerator {

    public static void generate(String template, Map<String, Object> data, String out) throws Exception {
        // 输出流, 输出为压缩文件
        ZipOutputStream zip = new ZipOutputStream(Files.newOutputStream(Paths.get(out)));
        // 用于生成目录
        ConfigureBuilder builder = Configure.builder();
        builder.bind("catalog", new TOCRenderPolicy());
        XWPFTemplate resultWord = XWPFTemplate.compile(template, builder.build()).render(data);
        // 写出为压缩文件
        zip.putNextEntry(new ZipEntry("word报告.docx"));
        resultWord.write(zip);
        zip.flush();
        zip.closeEntry();
        IOUtils.closeQuietly(zip);
    }

    public static TableRenderData generateTableData(String[] title, List<String[]> rowList) {
        Tables.TableBuilder table = Tables.of().center();
        // 设置标题及样式
        table.addRow(Rows.of(title)
                .textColor("FFFFFF")
                .bgColor("4472C4")
                .textFontSize(10)
                .center()
                .create());
        for (String[] rowData : rowList) {
            table.addRow(Rows.of(rowData).create());
        }
        return table.create();
    }
}
