package fileManager;


import java.io.File;
import java.util.*;

public class FManager {

    private Map<String, Long> fileModify = new HashMap<>();
    private Set<String> files = new HashSet<>();

    private FConfig config;

    public FManager() {
    }

    public FManager(FConfig config) {
        this.config = config;
    }

    public void init() {
        managerFiles();
        startWatch();
    }

    private void managerFiles() {
        String dir = config.getDir();

        File file = new File(dir);

        if (!file.exists()) {
            System.out.println("目录不存在");
            return;
        }

        if (!file.isDirectory()) {
            System.out.println("不是目录");
            return;
        }

        File[] subFiles = file.listFiles();
        if (subFiles == null || subFiles.length == 0) {
            return;
        }
        for (File f : subFiles) {
            if (f.isDirectory()) {
                continue;
            }

            String absPath = f.getAbsolutePath();
            Long lastModifyTime = f.lastModified();
            if (!files.contains(absPath)) {
                System.out.println("new file add " + absPath);
                files.add(absPath);
                fileModify.put(absPath, lastModifyTime);
                continue;
            }

            if (fileModify.get(absPath).longValue() != lastModifyTime ) {
                System.out.println("file update " + absPath);
                fileModify.put(absPath, lastModifyTime);
            }
        }
    }

    private void startWatch() {
        new Thread(() -> {
            while (true) {
                try {
                Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
                managerFiles();
            }
        }).start();
    }
}
