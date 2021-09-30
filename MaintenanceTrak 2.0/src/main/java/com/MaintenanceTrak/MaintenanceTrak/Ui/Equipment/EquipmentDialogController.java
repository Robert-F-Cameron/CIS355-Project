package com.MaintenanceTrak.MaintenanceTrak.Ui.Equipment;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EquipmentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@FxmlView("/Equipment/addEquipmentDialog.fxml")
public class EquipmentDialogController {
    @Autowired
    EquipmentRepository equipmentRepository;

    private final FxWeaver fxWeaver;

    public EquipmentDialogController(EquipmentRepository equipmentRepository, FxWeaver fxWeaver) {
        this.equipmentRepository = equipmentRepository;
        this.fxWeaver = fxWeaver;

    }
    //Text Input fields
    @FXML private Label lDatabaseId;
    @FXML private TextField tfEquipmentId;
    @FXML private TextField tfName;
    @FXML private TextField tfEquipmentLocation;
    //Checkbox to mark if equipment is deployed or not.
    @FXML private CheckBox cbDeployed;
    //Dialog Buttons
    @FXML private Button addButton;
    @FXML private Button cancelButton;
    @FXML private Button deleteButton;

    @FXML private void addEquipment(ActionEvent event) throws IOException {
        EquipmentTableController equipmentTable = fxWeaver.loadController(EquipmentTableController.class);

        Long id = null;
        Equipment data;

        //Gets the user inputs from the dialog for the equipment constructors.
        if(!lDatabaseId.getText().equals("")) {
            id = Long.parseLong(lDatabaseId.getText().trim());
        }
        String equipmentId = tfEquipmentId.getText().trim();
        String name = tfName.getText().trim();
        String equipmentLocation = tfEquipmentLocation.getText().trim();
        boolean deployed = cbDeployed.isSelected();
        //If the ID is null a new equipment item is added to the database
        //otherwise the if the ID is not null that equipment item is updated in the database.
        if(id != null){
            data = new Equipment(id, equipmentId, name, equipmentLocation, deployed);
        } else{
            data = new Equipment(equipmentId, name, equipmentLocation, deployed);
        }

        equipmentRepository.save(data);
        equipmentTable.loadEquipment();
        closeStage();
    }

    //Implementation for the cancel button
    @FXML private void cancel(ActionEvent event) throws IOException {
        closeStage();
    }

    //Deletes the equipment item from the database
    @FXML private void delete(ActionEvent event) throws IOException {
        EquipmentTableController equipmentTable = fxWeaver.loadController(EquipmentTableController.class);
        Long id = Long.parseLong(lDatabaseId.getText().trim());
        equipmentRepository.deleteById(id);
        equipmentTable.loadEquipment();
        closeStage();
    }

    //Loads an existing piece of equipment from the tableView.
    @FXML public void loadExisting(Equipment equipment){
        lDatabaseId.setText(String.valueOf(equipment.getId()));
        tfEquipmentId.setText(equipment.getEquipmentId());
        tfName.setText(equipment.getName());
        tfEquipmentLocation.setText(equipment.getEquipmentLocation());
        cbDeployed.setSelected(equipment.isDeployed());
        deleteButton.setManaged(true);
        deleteButton.setVisible(true);
    }

    //Closes the dialog.
    private void closeStage() throws IOException {
        Stage stage  = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}
