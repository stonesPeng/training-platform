package me.stone.training.platform.spring4all.camunda;

import org.camunda.bpm.client.ExternalTaskClient;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

/**
 * @author honor_stone@163.com
 * @description default description
 * @date 2021/12/21 14:12
 */
public class ChargeCardWorker {

    private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());


    public static void main(String[] args) {
        final ExternalTaskClient client = ExternalTaskClient.create()
            .baseUrl("http://127.0.0.1:8080/engine-rest")
            .asyncResponseTimeout(10000)
            .build();

        client.subscribe("charge-card")
            .lockDuration(1000)
            .handler((externalTask, externalTaskService) -> {
                final String item = (String)externalTask.getVariable("item");
                final Long amount = (Long) externalTask.getVariable("amount");
                LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");
                try {
                    Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                externalTaskService.complete(externalTask);
            }).open();
    }


}
