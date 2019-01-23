package ppt;

import ppt.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支持ppt2010 不支持ppt2016 其它版本未测试
 * */

public class App {

    public static void main(String[] args) {
        text();
    }


    /**
     * 文本
     * */
    private static void text() {
        String template = "E:\\ppttest\\text.pptx";
        String result = "E:\\ppttest\\textresult.pptx";

        SlideData slideData = new SlideData();

        Map<String, String> textes = new HashMap<>();
        textes.put("text1", "新中关购物中心");
        textes.put("text2", "周边分析");
        textes.put("image1", "E:\\ppttest\\page3_img.jpg");
        textes.put("image2", "E:\\ppttest\\page4_img.jpg");
        slideData.setTextMap(textes);


        Map<Integer, SlideData> pageTable = new HashMap<>();
        pageTable.put(1, slideData);
        PowerPointGenerator.generatorPowerPoint(template, result, pageTable);
    }


    /**
     * 饼图 支持份数的增减
     */
    private static void pie() {
        String template = "E:\\ppttest\\pie.pptx";
        String result = "E:\\ppttest\\pieresult.pptx";
        List<ChartData> pieData = getPieChartData();
        SlideData slideData = new SlideData();
        slideData.setChartDataList(pieData);
        Map<Integer, SlideData> map = new HashMap<>();
        map.put(1, slideData);

        PowerPointGenerator.generatorPowerPoint(template, result, map);
    }


    /**
     * 折线图
     * 支持维度的增减
     * */
    private static void line() {
        String template = "E:\\ppttest\\line.pptx";
        String result = "E:\\ppttest\\lineresult.pptx";
        SlideData slideData = new SlideData();
        List<ChartData> chartData = getLineData();
        slideData.setChartDataList(chartData);
        Map<Integer, SlideData> map = new HashMap<>();
        map.put(1, slideData);

        PowerPointGenerator.generatorPowerPoint(template, result, map);

    }


    /**
     * 柱状图
     * 不支持新增图例
     */
    private static void zhuzhuangtu() {
        String template = "E:\\ppttest\\2010demo.pptx";
        String resultFile = "E:\\ppttest\\2010result.pptx";

        //页柱状图
        Map<Integer, SlideData> map = new HashMap<>();
        SlideData slideData6 = new SlideData();
        List<ChartData> chartData6 = getChartData2();
        Map<String, String> textInfo = getTextDataTest2();
        slideData6.setTextMap(textInfo);
        slideData6.setChartDataList(chartData6);
        map.put(1, slideData6);

        PowerPointGenerator.generatorPowerPoint(template, resultFile ,map);
    }


    /**
     * 柱状图
     * 可以修改图例的名称 可以修改簇名
     *
     * 2016存在兼容问题
     * 插入的文本可以修改 但是不能修改图标自己的标题
     * 不支持增加横坐标
     * */
     private static List<ChartData> getChartData2() {
        List<ChartCategory> categoryDataList = new ArrayList<>();
        ChartCategory categoryData = new ChartCategory("A1", 0.3);
        ChartCategory categoryData2 = new ChartCategory("B1", 0.4);
        ChartCategory categoryData3 = new ChartCategory("C1", 0.5);
        ChartCategory categoryData4 = new ChartCategory("D1", 0.6);
        //ChartCategory categoryData5 = new ChartCategory("E1", 0.6);
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);
        categoryDataList.add(categoryData3);
        categoryDataList.add(categoryData4);
        //categoryDataList.add(categoryData5);


        List<ChartCategory> categoryDataList1 = new ArrayList<>();
        ChartCategory categoryData1 = new ChartCategory("A1", 0.3);
        ChartCategory categoryData12 = new ChartCategory("B1", 0.4);
        ChartCategory categoryData13 = new ChartCategory("C1", 0.6);
        ChartCategory categoryData14 = new ChartCategory("D1", 0.8);
       // ChartCategory categoryData15 = new ChartCategory("E1", 0.8);
        categoryDataList1.add(categoryData1);
        categoryDataList1.add(categoryData12);
        categoryDataList1.add(categoryData13);
        categoryDataList1.add(categoryData14);
        //categoryDataList1.add(categoryData15);

        List<ChartCategory> categoryDataList2 = new ArrayList<>();
        ChartCategory categoryData21 = new ChartCategory("A1", 0.3);
        ChartCategory categoryData22 = new ChartCategory("B1", 0.2);
        ChartCategory categoryData23 = new ChartCategory("C1", 0.1);
        ChartCategory categoryData24 = new ChartCategory("D1", 0.8);
        //ChartCategory categoryData25 = new ChartCategory("E1", 0.8);
        categoryDataList2.add(categoryData21);
        categoryDataList2.add(categoryData22);
        categoryDataList2.add(categoryData23);
        categoryDataList2.add(categoryData24);
        //categoryDataList2.add(categoryData25);

         List<ChartCategory> categoryDataList3 = new ArrayList<>();
         ChartCategory categoryData31 = new ChartCategory("A1", 0.3);
         ChartCategory categoryData32 = new ChartCategory("B1", 0.2);
         ChartCategory categoryData33 = new ChartCategory("C1", 0.1);
         ChartCategory categoryData34 = new ChartCategory("D1", 0.8);
         categoryDataList3.add(categoryData31);
         categoryDataList3.add(categoryData32);
         categoryDataList3.add(categoryData33);
         categoryDataList3.add(categoryData34);


        List<ChartSeries> seriesDataList = new ArrayList<>();
        ChartSeries seriesData = new ChartSeries();
        seriesData.setSeriesName("TEST1");
        seriesData.setChartCategoryList(categoryDataList);

        ChartSeries seriesData1 = new ChartSeries();
        seriesData1.setSeriesName("TEST2");
        seriesData1.setChartCategoryList(categoryDataList1);

        ChartSeries seriesData2 = new ChartSeries();
        seriesData2.setSeriesName("TEST3");
        seriesData2.setChartCategoryList(categoryDataList2);

         ChartSeries seriesData3 = new ChartSeries();
         seriesData3.setSeriesName("TEST4");
         seriesData3.setChartCategoryList(categoryDataList3);

        seriesDataList.add(seriesData);
        seriesDataList.add(seriesData1);
        seriesDataList.add(seriesData2);
        seriesDataList.add(seriesData3);
        //seriesDataList.add(seriesData2);


        ChartData chartData = new ChartData();
        chartData.setChartSeriesList(seriesDataList);

        List<ChartData> chartDataList = new ArrayList<>();
        chartDataList.add(chartData);
        return chartDataList;
    }

    private static Map<String, String> getTextDataTest2() {
        Map<String, String> textMap = new HashMap<>();
        textMap.put("title", "这是标题");
        return textMap;
    }

    private static List<ChartData> getPieChartData() {
        List<ChartCategory> categoryDataList = new ArrayList<>();
        ChartCategory categoryData = new ChartCategory("2008", 8.2);
        ChartCategory categoryData2 = new ChartCategory("2009", 3.2);
        ChartCategory categoryData3 = new ChartCategory("2010", 2.6);
       /* ChartCategory categoryData4 = new ChartCategory("2011", 3.6);
        ChartCategory categoryData5 = new ChartCategory("2012", 4.6);*/
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);
        categoryDataList.add(categoryData3);
       /* categoryDataList.add(categoryData4);
        categoryDataList.add(categoryData5);*/

        List<ChartSeries> seriesDataList = new ArrayList<>();
        ChartSeries seriesData = new ChartSeries();
        seriesData.setSeriesName("销售额");
        seriesData.setChartCategoryList(categoryDataList);
        seriesDataList.add(seriesData);

        ChartData chartData = new ChartData();
        chartData.setChartSeriesList(seriesDataList);

        List<ChartData> chartDataList = new ArrayList<>();
        chartDataList.add(chartData);
        return chartDataList;
    }

    private static List<ChartData> getLineData() {
        List<ChartCategory> categoryDataList = new ArrayList<>();
        ChartCategory categoryData = new ChartCategory("1.1", 0.123);
        ChartCategory categoryData2 = new ChartCategory("2.1", 0.84);
        ChartCategory categoryData3 = new ChartCategory("3.1", 0.53);
        ChartCategory categoryData4 = new ChartCategory("4.1", 0.262);
        ChartCategory categoryData5 = new ChartCategory("5.1", 1.262);
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);
        categoryDataList.add(categoryData3);
        categoryDataList.add(categoryData4);
        categoryDataList.add(categoryData5);


        List<ChartCategory> categoryDataList1 = new ArrayList<>();
        ChartCategory categoryData1 = new ChartCategory("1.1", 0.93);
        ChartCategory categoryData12 = new ChartCategory("2.1", 0.84);
        ChartCategory categoryData13 = new ChartCategory("3.1", 0.55);
        ChartCategory categoryData14 = new ChartCategory("4.1", 0.181);
        ChartCategory categoryData15 = new ChartCategory("5.1", 0.56);
        categoryDataList1.add(categoryData1);
        categoryDataList1.add(categoryData12);
        categoryDataList1.add(categoryData13);
        categoryDataList1.add(categoryData14);
        categoryDataList1.add(categoryData15);

      /*  List<ChartCategory> categoryDataList2 = new ArrayList<>();
        ChartCategory categoryData21 = new ChartCategory("A", 0.51);
        ChartCategory categoryData22 = new ChartCategory("B", 0.71);
        ChartCategory categoryData23 = new ChartCategory("C", 0.558);
        ChartCategory categoryData24 = new ChartCategory("D", 0.32);
        categoryDataList2.add(categoryData21);
        categoryDataList2.add(categoryData22);
        categoryDataList2.add(categoryData23);
        categoryDataList2.add(categoryData24);*/



        List<ChartSeries> seriesDataList = new ArrayList<>();
        ChartSeries seriesData = new ChartSeries();
        seriesData.setSeriesName("test1");
        seriesData.setChartCategoryList(categoryDataList);

        ChartSeries seriesData1 = new ChartSeries();
        seriesData1.setSeriesName("test2");
        seriesData1.setChartCategoryList(categoryDataList1);

        /*ChartSeries seriesData2 = new ChartSeries();
        seriesData2.setSeriesName("test3");
        seriesData2.setChartCategoryList(categoryDataList2);*/


        seriesDataList.add(seriesData);
        seriesDataList.add(seriesData1);
        //seriesDataList.add(seriesData2);


        ChartData chartData = new ChartData();
        chartData.setChartSeriesList(seriesDataList);

        List<ChartData> chartDataList = new ArrayList<>();
        chartDataList.add(chartData);
        return chartDataList;
    }

}
