package com.bootstrap;

import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/2/24
 */
public class TomcatServer {

    public void start() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setHostname("127.0.0.1");
        tomcat.setBaseDir(".");
        configServer(tomcat.getServer());
        tomcat.getEngine();
        configHost(tomcat.getHost());
        configConnector(tomcat.getConnector());
        try {
            tomcat.addWebapp("/",new File("springrestful/target/springrestful.war").getAbsolutePath());
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException e) {
            e.printStackTrace();
        }catch (LifecycleException e){
            e.printStackTrace();
        }
    }

    private void configServer(Server server) {
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);
    }

    private void configHost(Host host) {
        host.setAppBase(System.getProperty("user.dir") + "/springrestful" );
    }

    private void configConnector(Connector connector) {
        connector.setURIEncoding("UTF-8");
        connector.setMaxPostSize(0);
        connector.setAttribute("maxThreads", 200);
        connector.setAttribute("acceptCount", 100);
        connector.setAttribute("disableUploadTimeout", true);
        connector.setAttribute("enableLookups", false);
    }
}
