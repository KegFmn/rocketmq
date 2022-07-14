package com.likc.rockermqtest.consumer;

import com.likc.rockermqtest.dto.MessageDTO;
import com.likc.rockermqtest.mq.MqPushListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author likc
 * @date 2022/7/14
 * @description
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "rocketmq-demo",
        consumerGroup = "rocketmq-consumer-group"
)
public class MqPushConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private MqPushListener mqPushListener;

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("消息：{}", messageExt.getBody());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        //设置从当前时间开始消费
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);;
        defaultMQPushConsumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
        //设置最大重试次数.默认16次
        defaultMQPushConsumer.setMaxReconsumeTimes(10);
        //设置一次性消费数量
        defaultMQPushConsumer.setPullBatchSize(1);
        //设置监听者
        defaultMQPushConsumer.setMessageListener(mqPushListener);
    }

}
