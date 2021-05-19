package me.stone.training.platform.training.spring4all.rabbitmq.demo.configure;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/5/18 8:42
 */
@Configuration
public class RabbitMqConfig {


    @Bean("topicExchange")
    public TopicExchange topicExchange() {
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("FANOUT_EXCHANGE");
    }

    @Bean("zeroQueue")
    public Queue zeroQueue() {
        final Map<String, Object> args = Stream.of(new Object[][]{{"x-message-ttl", 6000}}).collect(Collectors.toMap(x -> (String) x[0], x -> x[1]));
        return new Queue("ZERO_QUEUE", false, false, true, args);
    }

    @Bean("firstQueue")
    public Queue firstQueue() {
        return new Queue("FIRST_QUEUE");
    }

    @Bean("secondQueue")
    public Queue secondQueue() {
        return new Queue("SECOND_QUEUE");
    }

    @Bean
    public Binding bindSecond(@Qualifier("firstQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#.stone.#");
    }

    @Bean
    public Binding bindThird(@Qualifier("secondQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


}
