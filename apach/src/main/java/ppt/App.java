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
        line();
    }


    //饼图
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


    //柱状图
    private static void zhuzhuangtu() {
        String template = "E:\\2010demo.pptx";
        String resultFile = "E:\\2010result.pptx";

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
     * 插入的文本可以修改 但是不能修改图标自己的标题
     * */
     private static List<ChartData> getChartData2() {
        List<ChartCategory> categoryDataList = new ArrayList<>();
        ChartCategory categoryData = new ChartCategory("A1", 0.3);
        ChartCategory categoryData2 = new ChartCategory("B1", 0.4);
        ChartCategory categoryData3 = new ChartCategory("C1", 0.5);
        ChartCategory categoryData4 = new ChartCategory("D1", 0.6);
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);
        categoryDataList.add(categoryData3);
        categoryDataList.add(categoryData4);


        List<ChartCategory> categoryDataList1 = new ArrayList<>();
        ChartCategory categoryData1 = new ChartCategory("A1", 0.3);
        ChartCategory categoryData12 = new ChartCategory("B1", 0.4);
        ChartCategory categoryData13 = new ChartCategory("C1", 0.6);
        ChartCategory categoryData14 = new ChartCategory("D1", 0.8);
        categoryDataList1.add(categoryData1);
        categoryDataList1.add(categoryData12);
        categoryDataList1.add(categoryData13);
        categoryDataList1.add(categoryData14);

        List<ChartCategory> categoryDataList2 = new ArrayList<>();
        ChartCategory categoryData21 = new ChartCategory("A1", 0.3);
        ChartCategory categoryData22 = new ChartCategory("B1", 0.2);
        ChartCategory categoryData23 = new ChartCategory("C1", 0.1);
        ChartCategory categoryData24 = new ChartCategory("D1", 0.8);
        categoryDataList2.add(categoryData21);
        categoryDataList2.add(categoryData22);
        categoryDataList2.add(categoryData23);
        categoryDataList2.add(categoryData24);


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

        seriesDataList.add(seriesData);
        seriesDataList.add(seriesData1);
        seriesDataList.add(seriesData2);


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
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);
        categoryDataList.add(categoryData3);

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
        ChartCategory categoryData = new ChartCategory("A", 0.123);
        ChartCategory categoryData2 = new ChartCategory("B", 0.84);
        ChartCategory categoryData3 = new ChartCategory("C", 0.53);
        ChartCategory categoryData4 = new ChartCategory("D", 0.262);
        categoryDataList.add(categoryData);
        categoryDataList.add(categoryData2);
        categoryDataList.add(categoryData3);
        categoryDataList.add(categoryData4);


        List<ChartCategory> categoryDataList1 = new ArrayList<>();
        ChartCategory categoryData1 = new ChartCategory("A", 0.93);
        ChartCategory categoryData12 = new ChartCategory("B", 0.84);
        ChartCategory categoryData13 = new ChartCategory("C", 0.55);
        ChartCategory categoryData14 = new ChartCategory("D", 0.181);
        categoryDataList1.add(categoryData1);
        categoryDataList1.add(categoryData12);
        categoryDataList1.add(categoryData13);
        categoryDataList1.add(categoryData14);

        List<ChartCategory> categoryDataList2 = new ArrayList<>();
        ChartCategory categoryData21 = new ChartCategory("A", 0.51);
        ChartCategory categoryData22 = new ChartCategory("B", 0.71);
        ChartCategory categoryData23 = new ChartCategory("C", 0.558);
        ChartCategory categoryData24 = new ChartCategory("D", 0.32);
        categoryDataList2.add(categoryData21);
        categoryDataList2.add(categoryData22);
        categoryDataList2.add(categoryData23);
        categoryDataList2.add(categoryData24);



        List<ChartSeries> seriesDataList = new ArrayList<>();
        ChartSeries seriesData = new ChartSeries();
        seriesData.setSeriesName("test1");
        seriesData.setChartCategoryList(categoryDataList);

        ChartSeries seriesData1 = new ChartSeries();
        seriesData1.setSeriesName("test2");
        seriesData1.setChartCategoryList(categoryDataList1);

        ChartSeries seriesData2 = new ChartSeries();
        seriesData2.setSeriesName("test3");
        seriesData2.setChartCategoryList(categoryDataList2);


        seriesDataList.add(seriesData);
        seriesDataList.add(seriesData1);
        seriesDataList.add(seriesData2);


        ChartData chartData = new ChartData();
        chartData.setChartSeriesList(seriesDataList);

        List<ChartData> chartDataList = new ArrayList<>();
        chartDataList.add(chartData);
        return chartDataList;
    }

}
