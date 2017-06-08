package hj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by lh on 2017/4/23.
 */
public class NIOServer {

    private ServerSocketChannel serverSocketChannel;

    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    private Selector selector;

    private int port = 8777;

    //回话消息列表
    private static Map<SelectionKey, String> sessionMsgs = new HashMap<>();


    public NIOServer(int port) throws IOException {
        this.port = port;
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //默认是阻塞的
        serverSocketChannel.configureBlocking(false);
        //init selector
        selector = Selector.open();

        //与选择器建立关系
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("NIO 服务器已经初始化完毕");
    }

    // 监听机制
    public void listen() throws IOException {
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            } else {
                Set<SelectionKey> eventKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = eventKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //处理事件
                    handKey(selectionKey);
                    //移除
                    iterator.remove();
                }

            }
        }
    }

    private void handKey(SelectionKey selectionKey) throws IOException {
        //底层采用linux的可复用形式
        if (selectionKey.isAcceptable()) {
            SocketChannel client = serverSocketChannel.accept();
            //配置为非阻塞式
            client.configureBlocking(false);
            //改变注册器上的事件状态
            client.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            //通过注册事件key拿到事件内容
            SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
            int len = clientChanel.read(receiveBuffer);
            if (len > 0) {
                //拿数据前锁定一下
                receiveBuffer.flip();
                String msg = new String(receiveBuffer.array(), 0, len);
                //根据事件Key和消息建立一个关联关系
                sessionMsgs.put(selectionKey, msg);
                clientChanel.register(selector, SelectionKey.OP_WRITE);
            }
        } else if (selectionKey.isWritable()) {
            if (!sessionMsgs.containsKey(selectionKey))
                return;

            SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
            //写之前将缓存池中之前的信息清空
            sendBuffer.clear();//相当于outputStream.flush()
            sendBuffer.put((sessionMsgs.get(selectionKey) + "haha").getBytes());
            receiveBuffer.flip();
            socketChannel.write(sendBuffer);
            socketChannel.register(selector,SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) {
        try {
            new NIOServer(8777).listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
