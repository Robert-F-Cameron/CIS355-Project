package com.MaintenanceTrak.MaintenanceTrak;


import com.MaintenanceTrak.MaintenanceTrak.Ui.MainMenuController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.MaintenanceTrak.MaintenanceTrak.MaintenanceApplication.StageReadyEvent;

//Builds the stage for JavaFx when the stage ready event is published by the start() method in Maintenance Application this class.onApplicationEvent is invoked.
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private final String applicationTitle;
    private final FxWeaver fxWeaver;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        stage.setScene(new Scene(fxWeaver.loadView(MainMenuController.class), 800, 600));
        stage.setTitle(applicationTitle);
        stage.getIcons().add(new Image(MainMenuController.class.getResourceAsStream("/icon.png")));
        stage.show();
    }
}
