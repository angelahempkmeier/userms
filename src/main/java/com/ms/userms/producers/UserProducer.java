package com.ms.userms.producers;

import com.ms.userms.dtos.EmailDTO;
import com.ms.userms.entities.UserEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    final RabbitTemplate rabbitTemplate;
    @Value(value = "${broker.queue.emailms.name}")
    private String routingKey;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageEmail(UserEntity user){
        var emailDTO = new EmailDTO(
                user.getUserId(),
                user.getEmail(),
                "Cadastro realizado com sucesso.",
                user.getName() + ", seja bem vindo! \n Obrigada pelo cadastro."
        );

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);

    }


}
