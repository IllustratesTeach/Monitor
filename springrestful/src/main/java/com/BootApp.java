package com;

import com.bootstrap.TomcatServer;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/2/24
 */
public class BootApp {
    public static void main(String[] args){
        TomcatServer tomcatServer = new TomcatServer();
        tomcatServer.start();
    }
}
