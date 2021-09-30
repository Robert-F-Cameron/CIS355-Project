package com.MaintenanceTrak.MaintenanceTrak.Ui.Events;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.MxEvent;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.User;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories.EventRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
