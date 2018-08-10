package digester;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liuweiliang1
 * @since 2018/8/9.
 *
 * 摘要
 */
public class DigesterDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String text = " 1级菜单\n" +
                "  菜单名：体检中心\n" +
                "  菜单code：M-HEALTH-CENTER\n" +
                "  菜单权限码：ROLE_ITEM_HEALTH\n" +
                "  链接：vcp.jd.com/sub_health/center/init\n" +
                "  \n" +
                " 2级菜单 \n" +
                "  菜单名：商品体检\n" +
                "  \n" +
                " 3级菜单\n" +
                "  菜单名：体检商品列表\n" +
                "  菜单code：SM-ITEM-HEALTH-LIST\n" +
                "  菜单权限码：ROLE_ITEM_HEALTH_LIST\n" +
                "  链接： vcp.jd.com/sub_health/query/init";
        byte[] bytes = text.getBytes();
        messageDigest.update(bytes);
        byte[] digestBytes = messageDigest.digest();
        System.out.println(byte2Hex(digestBytes));
    }


    //转换成16进制的字符串
    public static String byte2Hex(byte[] b) {
        String hs="";
        String stmp="";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + ":";
            }
        }
        return hs.toUpperCase();
    }
}
