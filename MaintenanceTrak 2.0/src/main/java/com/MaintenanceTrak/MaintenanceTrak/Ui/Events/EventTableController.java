package com.MaintenanceTrak.MaintenanceTrak.Ui.Events;


import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.MxEvent;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.User;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EventRepository;
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
public class EventTableController {
    @Autowired
    EventRepository eventRepository;

    private final FxWeaver fxWeaver;

    public EventTableController(EventRepository eventRepository, FxWeaver fxWeaver) {
        this.eventRepository = eventRepository;
        this.fxWeaver = fxWeaver;
    }

    @FXML private TableView<MxEvent> eventTableView;
    @FXML private TableColumn<MxEvent, Long> eventId;
    @FXML private TableColumn<MxEvent, Equipment> equipmentId;
    @FXML private TableColumn<MxEvent, String> name;
    @FXML private TableColumn<MxEvent, String> description;
    @FXML private TableColumn<MxEvent, Date> dateOpened;
    @FXML private TableColumn<MxEvent, User> openedBy;
    @FXML private TableColumn<MxEvent, Date> dateClosed;
    @FXML private TableColumn<MxEvent, User> closedBy;

    private final ObservableList<MxEvent> mxEvents = FXCollections.observableArrayList();

    @FXML
    public Button addEventButton;

    @FXML
    public void initialize() throws IOException {
        eventTableView.setRowFactory(mxEvent ->{
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
        addEventButton.setTooltip(new Tooltip("Select to add new Event."));
        equipmentId.setCellValueFactory(new PropertyValueFactory<MxEvent, Equipment>("equipmentName"));
        name.setCellValueFactory(new PropertyValueFactory<MxEvent, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<MxEvent, String>("description"));
        dateOpened.setCellValueFactory(new PropertyValueFactory<MxEvent, Date>("dateOpened"));
        openedBy.setCellValueFactory(new PropertyValueFactory<MxEvent, User>("openedBy"));
        dateClosed.setCellValueFactory(new PropertyValueFactory<MxEvent, Date>("dateClosed"));
        closedBy.setCellValueFactory(new PropertyValueFactory<MxEvent, User>("dateClosed"));
        loadEvent();
    }

    @FXML void loadEvent(){
        mxEvents.removeAll();
        eventTableView.getItems().clear();
        mxEvents.addAll(eventRepository.findAll());
        eventTableView.setItems(mxEvents);
    }

    @FXML
    void handleAddEvent(ActionEvent event) throws IOException {
        Dialog<EventDialogController> eventDialogDialog = new Dialog<>();
        DialogPane dialogPane = fxWeaver.loadView(EventDialogController.class);
        eventDialogDialog.setDialogPane(dialogPane);
        eventDialogDialog.showAndWait();
    }

    @FXML
    void handleEdit(MxEvent event) throws IOException {
        EventDialogController eventDialogController = fxWeaver.loadController(EventDialogController.class);
        Dialog<EventDialogController> eventDialog = new Dialog<>();
        DialogPane dialogPane = fxWeaver.loadView(EventDialogController.class);
        eventDialogController.loadExisting(event);
        eventDialog.setDialogPane(dialogPane);
        eventDialog.showAndWait();
    }
}
