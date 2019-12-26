package com.atguigu.demo2;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class XfMyMq {

    public static final String EFAULT_BROKER_BIND_URL = "tcp://192.168.36.129:61616";
    //public static final String QUEUE_NAME = "queue0805";//队列
    public static final String TOPIC_NAME = "topic0805";//主题

    public static void main(String[] args) throws JMSException {
        //1 获得ActiveMQConnectionFactory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(EFAULT_BROKER_BIND_URL);
        //2 由ActiveMQConnectionFactory获得Connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //3 启动连接准备建立会话
        connection.start();
        //4 获得Session，两个参数先用默认
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.1 是否开启事务
        //4.2 签收模式
        //5 获得目的地，此例是队列
        Topic topic = session.createTopic(TOPIC_NAME);
        //6 获得消息消费者,消费什么内容？从哪里消费？
        MessageConsumer consumer = session.createConsumer(topic);

      /*
    异步非阻塞方式(监听器onMessage())
    订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器，
    当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法。*/
        consumer.setMessageListener(message-> {
            if(message!=null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("message consumer****"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
         /*同步阻塞方式receive() ，订阅者或接收者调用MessageConsumer的receive()方法来接收消息，
        receive()将一直阻塞
        receive(long timeout) 按照给定的时间阻塞，到时间自动退出*/
        //TextMessage textMessage = null;
       /* while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive(4000);//需要释放资源
            //TextMessage textMessage = (TextMessage) messageConsumer.receive();//不用释放资源
            if (null != textMessage) {
                System.out.println("*******接收到的消息" + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();
        System.out.println("******msg consu ok xiaofei");*/
    }
}