package com.cloud.user.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
        @Bean
        public Queue userQueue(){
            // 参数: 队列名称, 是否持久化
            return new Queue("queue-user", true);
        }
        @Bean
        public DirectExchange userExchange(){
            return new DirectExchange("user-exchange",true,false);
        }
        @Bean
        public Binding binding() {
            return BindingBuilder.bind(userQueue())
                    .to(userExchange())
                    .with("userRoutingKey");
        }
}
