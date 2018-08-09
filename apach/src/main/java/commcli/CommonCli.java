package commcli;

import org.apache.commons.cli.*;

import java.io.File;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author liuweiliang1
 * @since 2018/8/9.
 */
public class CommonCli {

    private static Options buildOptions() {
        // 无参数 直接返回信息
        Option help = new Option("h", "help", false, "list all");
        Option time = new Option("t", "current time");

        //带参数
        Option year = new Option("y", true, "当前是否是闰年");
        year.setArgName("year");
        Option file = new Option("f", true, "指定路径下的内容");
        year.setArgName("path");

        Option properties = Option.builder("p")
                .argName("parm")
                .desc("多个参数分割")
                .valueSeparator(':')
                .hasArgs()
                .build();

        Options options = new Options();
        options.addOption(help)
                .addOption(time)
                .addOption(year)
                .addOption(file)
                .addOption(properties);
        return options;
    }

    private static CommandLine parse(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public static void execute(String[] args) throws ParseException {
        CommandLine commandLine = parse(buildOptions(), args);

        if(commandLine.hasOption("h")) {
            printHelp(buildOptions());
        }

        if (commandLine.hasOption("t")) {
            System.out.println("参数t");
            System.out.println(Clock.systemDefaultZone().millis());
        }

        if (commandLine.hasOption("y")) {
            System.out.println("参数y--------");
            String date = commandLine.getOptionValue("y");
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
            if (localDate.isLeapYear()) {
                System.out.println("闰年");
            }
            else {
                System.out.println("平年");
            }
        }

        if (commandLine.hasOption("f")) {
            System.out.println("参数f-------");
            String path = commandLine.getOptionValue("f");
            File file = new File(path);
            if (file.isDirectory()) {
                String[] subFiles = file.list();
                Stream.of(subFiles)
                        .forEach(System.out::println);
            }
        }

        if (commandLine.hasOption("p")) {
            System.out.println("参数p------");
            String[] params = commandLine.getOptionValues("p");
            for (String param : params) {
                System.out.println(param);
            }
        }
    }

    private static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        //helpFormatter.setWidth(100); //设置每一行的宽度
        helpFormatter.printHelp("reborn", options);
    }
}
