
public class MysqlUtil {

    /**
     * 将下划线的sql列名转换为java驼峰式
     * */
    public String colNameConvert(String sqlName) {
        if (sqlName == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        StringBuilder builder = new StringBuilder();
        boolean b = false;
        for (int i = 0; i < sqlName.length(); i ++) {
            char cur = sqlName.charAt(i);
            if (cur == '_') {
               b = true;
               continue;
           }

           if (b) {
               if (cur >= 'a' && cur <= 'z') {
                   cur -= 32;
               }
               b = false;
           }

           builder.append(cur);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MysqlUtil().colNameConvert("_qwe_1tyy_uy"));
    }


}
