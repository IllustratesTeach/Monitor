package com.cloud;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="yuchen_1997_200486@126.com">yuchen</a>.
 * @since 2019/2/15
 */
public class CloudManager {

    private static Logger logger = LoggerFactory.getLogger(CloudManager.class);

    private String connectString = "";

    public CloudManager(String connectString){

        this.connectString = connectString;
    }
    /**
     * 用于标示是哪个工程的znode节点
     */
    public static final String ZK_PERJECT_NODE = "/txsvr";//7.2通讯服务器

    private static CountDownLatch latch = new CountDownLatch(1);

    private static Optional<ZooKeeper> zooKeeper = Optional.empty();


    /**
     * create zookeeper session And get zookeeper object
     * @return
     * @throws Exception
     */
    private Optional<ZooKeeper> getZooKeeper() throws Exception{

        if(!zooKeeper.isPresent()){
            zooKeeper = Optional.of(new ZooKeeper(connectString,15000
                    ,(event) ->{
                            if(event.getState() == Watcher.Event.KeeperState.SyncConnected){
                                latch.countDown();
                            }
            }));
        }
        return zooKeeper;
    }

    /**
     * create projectZNode on zookeeper
     * @throws Exception
     */
    public void createProjectPersistNode(){
        try{
            getZooKeeper().get().create(ZK_PERJECT_NODE
                    ,ZK_PERJECT_NODE.getBytes(Charset.forName("UTF-8"))
                    , ZooDefs.Ids.OPEN_ACL_UNSAFE
                    , CreateMode.PERSISTENT);
        }catch (KeeperException.ConnectionLossException ex){
            createProjectPersistNode();
        }catch(KeeperException.NodeExistsException ex){
            logger.info("project ZNode {} exists",ZK_PERJECT_NODE);
        }catch(Exception ex){
            logger.error("project ZNode create failed,{}",ex.getMessage());
        }
    }

    public void exist(String childrenNode){
        Stat stat  = new Stat();
        try {
            getZooKeeper().get().exists(childrenNode
                    ,(event) -> {
                        if(Watcher.Event.KeeperState.SyncConnected == event.getState()){

                            if(Watcher.Event.EventType.NodeDeleted == event.getType()){
                                //TODO:update state 0
                                logger.info("node not exist svr not run");
                            }
                            if(Watcher.Event.EventType.NodeCreated == event.getType()){
                                //TODO:update state 1
                                logger.info("node exist svr running");
                            }
                        }
                    }
                    ,existCallback
                    ,stat);
        } catch (Exception e) {
            logger.error("exist function error:{}",e.getMessage());
        }
    }

    private AsyncCallback.StatCallback existCallback = (rc, path, ctx, name) -> {
        switch(KeeperException.Code.get(rc)){
            case CONNECTIONLOSS:
            case OK:
            case NONODE:
                exist(path);
                break;
            default:
                break;
        }
    };


}
