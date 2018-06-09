package com.foresee.gpz.registry;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: guhao
 * @Date: 2018/6/2 22:52
 * @Description: zookeeper客户端工具类
 */
public class ZkClientUtil {

    private static Logger log = LoggerFactory.getLogger(ZkClientUtil.class);

    /**
     * 客户端实例
     */
    private static ZkClient zkClient;
    /**
     * 锁，保证只获取一个zkclient实例
     */
    private static Lock lock = new ReentrantLock();
    /**
     * zookeeper地址
     */
    private final static String ZOOKEEPER_ADDRESS = "127.0.0.1:2181";

    /**
     * 获取zkclient实例
     * @return
     */
    public static ZkClient getZkClient(){
        if(zkClient == null){
            init();
        }

        return zkClient;
    }

    /**
     * 初始化zkclient客户端
     */
    public static void init(){
        if(zkClient == null){
            try{
                lock.lock();
                if(zkClient == null){
                    zkClient = new ZkClient(
                            ZOOKEEPER_ADDRESS,
                            30 * 1000,
                            30 * 1000);

                    //注册状态监听器
                    zkClient.subscribeStateChanges(new IZkStateListener() {
                        @Override
                        public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                            //实例化时，该监听器还没有添加进去，所以没有执行到handleStateChanged方法
                           if(keeperState == Watcher.Event.KeeperState.SyncConnected){
                               log.info("zookeeper 客户端连接成功！");
                           }else if(keeperState == Watcher.Event.KeeperState.Disconnected){
                               log.info("zookeeper 客户端连接斷開！");
                           }
                        }

                        @Override
                        public void handleNewSession() throws Exception {
                            log.info("新建会话session...");
                        }

                        @Override
                        public void handleSessionEstablishmentError(Throwable throwable) throws Exception {

                        }
                    });
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //解锁，防止死锁
                lock.unlock();
            }
        }
    }

    public static String createNode(String path, boolean flag){
        if(flag){
            return getZkClient().createPersistentSequential(path, null);
        }else{
            return getZkClient().createEphemeralSequential(path, null);
        }
    }

    public static void main(String[] args) {
            String path = createNode("/ms", false);
            log.info("节点创建完毕！");

            //注册监听器。监听/ms节点下面的变化
            zkClient.subscribeDataChanges(path, new IZkDataListener(){

                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    log.info("{} 节点数据发生变化，当前数据是：{}", dataPath, data);
                }

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    log.info("{} 节点被删除！", dataPath);
                }
            });

            //添加数据
            zkClient.writeData(path, "test data");

            //删除临时节点
            zkClient.deleteRecursive(path);

    }

}
