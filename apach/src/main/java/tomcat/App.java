package tomcat;

import org.apache.catalina.*;
import org.apache.catalina.startup.Tomcat;
import tomcat.servlet.DemoServlet;

import java.util.concurrent.TimeUnit;


/**
 * 嵌入式tomcat启动
 * 核心类 org.apache.catalina.startup.Tomcat
 *
 * */
public class App {
    public static void main(String[] args) throws LifecycleException, InterruptedException {
        Tomcat tomcat = new Tomcat();
        tomcat.noDefaultWebXmlPath();
        Host host = tomcat.getHost();
        Context context = tomcat.addContext(host, "/", "E:\\WORKPLACE\\webapp");
        Wrapper wrapper = Tomcat.addServlet(context, "demo", new DemoServlet());
        wrapper.addMapping("/demo");
        tomcat.start();


        TimeUnit.DAYS.sleep(1);
    }
}
