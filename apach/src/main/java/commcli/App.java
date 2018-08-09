package commcli;

import org.apache.commons.cli.ParseException;

/**
 * @author liuweiliang1
 * @since 2018/8/9.
 * -h -t -y20081203 -f D:\git3333\practice\apach\src\main\java\commcli -pParma1:p2
 */
public class App {
    public static void main(String[] args) throws ParseException {
            CommonCli.execute(args);
    }
}
