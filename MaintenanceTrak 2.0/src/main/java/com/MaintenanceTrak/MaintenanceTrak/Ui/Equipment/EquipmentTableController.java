package com.MaintenanceTrak.MaintenanceTrak.Ui.Equipment;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EquipmentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("/Equipment/equipmentTable.fxml")
public class EquipmentTableController {
    @Autowired
    EquipmentRepository equipmentRepository;

    private final FxWeaver fxWeaver;

    EquipmentTableController(EquipmentRepository equipmentRepository, FxWeaver fxWeaver) {
        this.equipmentRepository = equipmentRepository;
        this.fxWeaver = fxWeaver;
    }
    @FXML private TableView<Equipment> tableView;
    @FXML private TableColumn<Equipment, String> equipmentId;
    @FXML private TableColumn<Equipment, String> name;
    @FXML private TableColumn<Equipment, String> equipmentLocation;
    @FXML private TableColumn<Equipment, Boolean> deployed;

    public ObservableList<Equipment> list = FXCollections.observableArrayList();

    @FXML
    public Button button;


    @FXML
    public void initialize() throws IOException{
        tableView.setRowFactory(equipment ->{
            TableRow<Equipment> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    Equipment rowData = row.getItem();
                    try {
                        handleEdit(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        button.setTooltip(new Tooltip("Select to add new equipment."));
        equipmentId.setCellValueFactory(new PropertyValueFactory<Equipment, String>("equipmentId"));
        name.setCellValueFactory(new PropertyValueFactory<Equipment, String>("name"));
        equipmentLocation.setCellValueFactory(new PropertyValueFactory<Equipment, String>("equipmentLocation"));
        deployed.setCellValueFactory(new PropertyValueFactory<Equipment, Boolean>("deployed"));
        loadEquipment();
    }

    @FXML void loadEquipment(){
        list.removeAll();
        tableView.getItems().clear();
        list.addAll(equipmentRepository.findAll());
        tableView.setItems(list);
    }

    @FXML
    void handleAdd(ActionEvent event) throws IOException {
        Dialog<EquipmentDialogController> equipmentControllerDialog = new Dialog<>();
        DialogPane dialogPane = fxWeaver.loadView(EquipmentDialogController.class);
        equipmentControllerDialog.setDialogPane(dialogPane);
        equipmentControllerDialog.showAndWait();
    }

    @FXML
    void handleEdit(Equipment equipment) throws IOException {
        EquipmentDialogController equipmentDialogController = fxWeaver.loadController(EquipmentDialogController.class);
        Dialog<EquipmentDialogController> equipmentDialog = new Dialog<>();
        DialogPane dialogPane = fxWeaver.loadView(EquipmentDialogController.class);
        equipmentDialogController.loadExisting(equipment);
        equipmentDialog.setDialogPane(dialogPane);
        equipmentDialog.showAndWait();
    }

    @FXML
    public ObservableList<Equipment> getList() {
        return list;
    }
}
