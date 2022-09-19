package com.example.fileoperation.word;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试用数据实体list
 */
@Data
public class DataEntity {

    /**
     * 扫描时间
     */
    private Integer index;

    /**
     * url总数
     */
    private Integer urlCount;

    /**
     * 命中数
     */
    private Integer hitCount;

    /**
     * 生成测试数据
     *
     * @param count 生成数据量
     */
    public static List<DataEntity> getTestList(int count) {
        List<DataEntity> testList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            DataEntity data = new DataEntity();
            data.setIndex(i + 1);
            data.setHitCount(random.nextInt(20));
            data.setUrlCount(random.nextInt(20));
            testList.add(data);
        }
        return testList;
    }

}
