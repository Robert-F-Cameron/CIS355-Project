package com.MaintenanceTrak.MaintenanceTrak.Ui.Events;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.MxEvent;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EquipmentRepository;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EventRepository;
import com.MaintenanceTrak.MaintenanceTrak.Ui.Equipment.EquipmentTableController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Component
@FxmlView("/Events/addEventDialog.fxml")
public class EventDialogController {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    private final FxWeaver fxWeaver;

    public EventDialogController(EventRepository eventRepository, FxWeaver fxWeaver) {
        this.eventRepository = eventRepository;
        this.fxWeaver = fxWeaver;

    }
    //Input fields
    @FXML private ComboBox<Equipment> cbEquipmentId;
    @FXML private Label lDatabaseId;
    @FXML private DatePicker dDateOpened;
    @FXML private TextField tfEventName;
    @FXML private TextArea taDescription;
    @FXML private Button submitButton;

    @FXML private void initialize() throws IOException {
        ObservableList<Equipment> equipment = FXCollections.observableArrayList();
        equipment.addAll(equipmentRepository.findAll());
        cbEquipmentId.setItems(equipment);
        //This sets the Combo Box Equipment Objects to display just their name
        cbEquipmentId.setConverter(new StringConverter<Equipment>(){

            @Override
            public String toString(Equipment object) {
                return object == null ? null : object.getName();
            }

            @Override
            public Equipment fromString(String string) {
                return cbEquipmentId.getItems().stream().filter(equipment -> equipment.getName().equals(string)).findAny().orElse(null);
            }

        });
        dDateOpened.setValue(LocalDate.now());
    }

    @FXML private void addEvent(ActionEvent event) throws IOException {
        EventTableController eventsTable = fxWeaver.loadController(EventTableController.class);
        MxEvent data;
        Long id = null;

        if(!lDatabaseId.getText().equals("")) {
            id = Long.parseLong(lDatabaseId.getText().trim());
        }
        Long equipmentId = cbEquipmentId.getSelectionModel().getSelectedItem().getId();
        String name = tfEventName.getText().trim();
        LocalDate dateOpened = dDateOpened.getValue();
        String description = taDescription.getText().trim();
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);

        if(id != null){
            data = new MxEvent(id, name, description, dateOpened, equipment.get());
        } else{
            data = new MxEvent(name, description, dateOpened, equipment.get());
        }



        eventRepository.save(data);
        eventsTable.loadEvent();
        closeStage();
    }

    //Implementation for the cancel button
    @FXML private void cancel(ActionEvent event) throws IOException {
        closeStage();
    }


    //Loads an existing piece of equipment from the tableView.
    @FXML public void loadExisting(MxEvent event){
        lDatabaseId.setText(String.valueOf(event.getId()));
        cbEquipmentId.setValue(event.getEquipment());
        tfEventName.setText(event.getName());
        taDescription.setText(event.getDescription());
        dDateOpened.setValue(event.getDateOpened());
    }

    //Closes the dialog.
    private void closeStage() throws IOException {
        Stage stage  = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }

}
