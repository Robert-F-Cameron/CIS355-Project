package com.MaintenanceTrak.MaintenanceTrak.Ui.Events;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.MxEvent;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.User;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EventRepository;
import com.MaintenanceTrak.MaintenanceTrak.Ui.Equipment.EquipmentDialogController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;


@Component
@FxmlView("/Events/eventTable.fxml")
public class EventsTableController {
    @Autowired
    EventRepository eventRepository;

    private final FxWeaver fxWeaver;

    public EventsTableController(EventRepository eventRepository, FxWeaver fxWeaver) {
        this.eventRepository = eventRepository;
        this.fxWeaver = fxWeaver;
    }

    @FXML private TableView<MxEvent> tableView;
    @FXML private TableColumn<MxEvent, String> equipmentId;
    @FXML private TableColumn<MxEvent, String> name;
    @FXML private TableColumn<MxEvent, String> description;
    @FXML private TableColumn<MxEvent, Date> dateOpened;
    @FXML private TableColumn<MxEvent, User> openedBy;
    @FXML private TableColumn<MxEvent, Date> dateClosed;
    @FXML private TableColumn<MxEvent, User> closedBy;

    public ObservableList<MxEvent> list = FXCollections.observableArrayList();

    @FXML
    public Button button;

    @FXML
    public void initialize() throws IOException {
        tableView.setRowFactory(equipment ->{
            TableRow<MxEvent> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    MxEvent rowData = row.getItem();
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
        equipmentId.setCellValueFactory(new PropertyValueFactory<MxEvent, String>("equipmentId"));
        name.setCellValueFactory(new PropertyValueFactory<MxEvent, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<MxEvent, String>("description"));
        dateOpened.setCellValueFactory(new PropertyValueFactory<MxEvent, Date>("dateOpened"));
        openedBy.setCellValueFactory(new PropertyValueFactory<MxEvent, User>("openedBy"));
        dateClosed.setCellValueFactory(new PropertyValueFactory<MxEvent, Date>("dateClosed"));
        closedBy.setCellValueFactory(new PropertyValueFactory<MxEvent, User>("dateClosed"));
        loadEquipment();
    }

    @FXML void loadEquipment(){
        list.removeAll();
        tableView.getItems().clear();
        list.addAll(eventRepository.findAll());
        tableView.setItems(list);
    }

    @FXML
    void handleAdd(ActionEvent event) throws IOException {
        Dialog<EquipmentDialogController> equipmentControllerDialog = new Dialog<>();
        DialogPane dialogPane = fxWeaver.loadView(EventDialogController.class);
        equipmentControllerDialog.setDialogPane(dialogPane);
        equipmentControllerDialog.showAndWait();
    }

    @FXML
    void handleEdit(MxEvent event) throws IOException {
        EquipmentDialogController eventDialogController = fxWeaver.loadController(EventDialogController.class);
        Dialog<EquipmentDialogController> equipmentControllerDialog = new Dialog<>();
        DialogPane dialogPane = fxWeaver.loadView(EventDialogController.class);
        eventDialogController.loadExisting(event);
        eventDialogController.setDialogPane(dialogPane);
        eventDialogController.showAndWait();
    }
}
