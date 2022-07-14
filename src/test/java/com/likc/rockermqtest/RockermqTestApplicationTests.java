package com.likc.rockermqtest;

import com.likc.rockermqtest.dto.MessageDTO;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@SpringBootTest
class RockermqTestApplicationTests {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void contextLoads() throws IOException {
        for (int i = 0; i < 2; i++) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(i);
            messageDTO.setMsg("测试"+ i);
            rocketMQTemplate.syncSend("rocketmq-demo" ,messageDTO);
        }

    }

}
