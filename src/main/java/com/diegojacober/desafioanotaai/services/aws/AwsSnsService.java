package com.diegojacober.desafioanotaai.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AwsSnsService {

    @Autowired
    private AmazonSNS snsClient;

    @Autowired
    @Qualifier("catalogEventsTopic")
    private Topic catalogTopic;

    public void publish(MessageDTO messageData) {
        snsClient.publish(catalogTopic.getTopicArn(), messageData.toString());
    }
}
