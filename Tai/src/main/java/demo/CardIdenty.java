package demo;

import cn.xsshome.taip.ocr.TAipOcr;

public class CardIdenty {

    public static void main(String[] args) throws Exception {
        String appId = "1106897271";
        String appkey = "kB6H3ZUxORxyZUfT";
        String ecardPaht = "/home/reborn/download/idcard.jpg";

        // 初始化一个TAipOcr
        TAipOcr aipOcr = new TAipOcr(appId, appkey);
        String rs = aipOcr.idcardOcr(ecardPaht, 0);

        System.out.println(rs);
    }
}
