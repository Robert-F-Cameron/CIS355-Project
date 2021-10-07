package com.MaintenanceTrak.MaintenanceTrak.Ui.Events;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.MxEvent;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EquipmentRepository;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EventRepository;
import com.MaintenanceTrak.MaintenanceTrak.Ui.Equipment.EquipmentTableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    //Text Input fields
    @FXML private ComboBox<Equipment> cbEquipmentId;
    @FXML private DatePicker dDateOpened;
    @FXML private TextField tfEventName;
    @FXML private TextArea taDescription;
    @FXML private Label lDatabaseId;
    //Dialog Buttons
    @FXML private Button submitButton;
//    @FXML private Button cancelButton;

    @FXML private void initialize() throws IOException {
        EquipmentTableController equipmentTable = fxWeaver.loadController(EquipmentTableController.class);
        cbEquipmentId.setItems(equipmentTable.getList());
    }

    @FXML private void addEvent(ActionEvent event) throws IOException {
        EventTableController eventsTable = fxWeaver.loadController(EventTableController.class);

        Long id = null;
        MxEvent data;

        //Gets the user inputs from the dialog for the equipment constructors.
//        if(!lDatabaseId.getText().equals("")) {
//            id = Long.parseLong(lDatabaseId.getText().trim());
//        }
        Long equipmentId = Long.parseLong(cbEquipmentId.getSelectionModel().getSelectedItem().getEquipmentId());
        String name = tfEventName.getText().trim();
        LocalDate dateOpened = dDateOpened.getValue();
        String description = taDescription.getText().trim();
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);
        //If the ID is null a new equipment item is added to the database
        //otherwise the if the ID is not null that equipment item is updated in the database.
//        if(id != null){
//            data = new MxEvent(name, description, dateOpened, equipment.get());
//        } else{
            data = new MxEvent(name, description, dateOpened, equipment.get());
        //}

        eventRepository.save(data);
        eventsTable.loadEvent();
        closeStage();
    }

    //Implementation for the cancel button
    @FXML private void cancel(ActionEvent event) throws IOException {
        closeStage();
    }

    //Deletes the equipment item from the database
//    @FXML private void delete(ActionEvent event) throws IOException {
//        EventsTableController eventsTable = fxWeaver.loadController(EventsTableController.class);
//        Long id = Long.parseLong(lDatabaseId.getText().trim());
//        equipmentRepository.deleteById(id);
//        eventsTable.loadEvent();
//        closeStage();
//    }

    //Loads an existing piece of equipment from the tableView.
    @FXML public void loadExisting(MxEvent event){
        lDatabaseId.setText(String.valueOf(event.getId()));
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
