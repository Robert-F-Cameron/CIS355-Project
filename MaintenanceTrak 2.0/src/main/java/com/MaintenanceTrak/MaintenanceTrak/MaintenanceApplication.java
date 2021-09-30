package com.MaintenanceTrak.MaintenanceTrak;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class MaintenanceApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    //The Init method is what builds the SpringBoot application
    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(MaintenanceTrakApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }
        public Stage getStage(){
            return ((Stage) getSource());
        }
    }
}

