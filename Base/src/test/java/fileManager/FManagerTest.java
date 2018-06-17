package fileManager;

import org.junit.Before;
import org.junit.Test;

public class FManagerTest {
    private FConfig fConfig = null;
    private FManager fManager = null;

    @Before
    public void initParam() {
        fConfig = new FConfig();
        fConfig.setDir("/home/reborn/workspace/practice/Base/src/main/java/fileManager");
        fManager = new FManager(fConfig);
    }
    @Test
    public void testWatch() {
        fManager.init();
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
