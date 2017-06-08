package hj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by lh on 2017/4/23.
 */
public class NIOClient {


    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    private Selector selector;

    private SocketChannel client;

    //需要绑定IP和端口
    private InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8777);

    public NIOClient() throws IOException {
        client = SocketChannel.open();
        client.configureBlocking(false);
        client.connect(inetSocketAddress);
        selector = Selector.open();//NIO中出师化就是采用open
        client.register(selector, SelectionKey.OP_CONNECT);

    }

    //连接建立完成，目的向服务器发送写请求和读请求
    public void clientServer() throws IOException {
        if(client.isConnectionPending())
        {
            client.finishConnect();
            System.out.println("已经连接至客户端");
            client.register(selector,SelectionKey.OP_WRITE);
        }
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext())
        {
            String msg = scanner.nextLine();
            if("".equals(msg.trim()))
            {
                continue;
            } else if ("Exit".equals(msg.trim())) {
                System.exit(0);
            }
            //处理信息
            handler(msg);

        }

    }

    private void handler(String msg) throws IOException {
        //默认是等待的
        boolean isWait = true;
        while(true)
        {
            int evenSize = selector.select();
            if(evenSize==0) {
                continue;
            }
            Set<SelectionKey> eventKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = eventKeys.iterator();
            while (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isWritable())
                    {
                        sendBuffer.clear();
                        sendBuffer.put(msg.getBytes());

                        sendBuffer.flip();
                        client.write(sendBuffer);
                        client.register(selector,SelectionKey.OP_READ);
                    }else  if(selectionKey.isReadable())
                    {
                        receiveBuffer.clear();
                        //receiveBuffer.flip();
                        int len = client.read(receiveBuffer);
                        if(len>0)
                        {
                            System.out.println("服务器返回信息为"+receiveBuffer.toString()+"-"+new String(receiveBuffer.array(),0,len));
                        }
                        client.register(selector,SelectionKey.OP_WRITE);
                        isWait = false;
                    }
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NIOClient().clientServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
