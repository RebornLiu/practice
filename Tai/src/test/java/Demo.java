import cn.xsshome.taip.ocr.TAipOcr;
import org.junit.BeforeClass;
import org.junit.Test;

public class Demo {

    private static String appId = "1106897271";
    private static String appKey = "kB6H3ZUxORxyZUfT";
    private static TAipOcr tAipOcr = null;

    @BeforeClass
    public static void init() {
        tAipOcr = new TAipOcr(appId, appKey);
    }

    @Test
    public void testIdCard() throws Exception {
        String path = "/home/reborn/download/idcard.jpg";
        String str = tAipOcr.idcardOcr(path, 0);
        System.out.println("idcard_0:");
        System.out.println(str);
    }

    @Test
    public void testWenZiIdenty() throws Exception {
        String path = "/home/reborn/download/idcard.jpg";
        String str = tAipOcr.generalOcr(path);
        System.out.println("wenzi:");
        System.out.println(str);
    }

    @Test
    public void testYinhangCar() throws Exception {
        String path = "/home/reborn/download/yinhang.jpg";
        String str = tAipOcr.creditcardOcr(path);
        System.out.println("yinhang:");
        System.out.println(str);
    }
}
