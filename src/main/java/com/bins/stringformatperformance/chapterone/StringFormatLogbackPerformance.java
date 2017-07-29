package com.bins.stringformatperformance.chapterone;

import com.bins.utils.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by songrongbin on 2017/7/29.
 */
public class StringFormatLogbackPerformance {

    private final static Logger logger = LoggerFactory.getLogger(StringFormatLogbackPerformance.class);


    private static int loopNum = 100;  // 循环次数

    public static void main(String args[]) {

        Param param = new Param();
        param.setId(555555L);
        param.setName("Andy Bin");

        Long start1 = DataUtil.getCurrentTime();
        for (int i = 0; i < loopNum; i++) {
            logger.info(String.format("test string format param, id:[%s], name:[%s]", param.getId(), param.getName()));
        }
        Long end1 = DataUtil.getCurrentTime();
        System.out.println("String format cost time:" + (end1 - start1));


        Long start2 = DataUtil.getCurrentTime();
        for (int i = 0; i < loopNum; i++) {
            logger.info("test placeholder param, id:{}, name:{}", param.getId(), param.getName());
        }
        Long end2 = DataUtil.getCurrentTime();
        System.out.println("log placeholder cost time:" + (end2 - start2));

    }

}
