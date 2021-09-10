package com.CIS355.MaintenanceTrak.UI.Tables.Equipment;

import com.CIS355.MaintenanceTrak.Persistence.Model.Equipment.Equipment;
import com.CIS355.MaintenanceTrak.Persistence.View.Equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EquipmentTable extends JTable {
    private JTable equipmentTable;
    private JPanel equipmentTablePanel;

    @Autowired
    EquipmentService equipmentService;

    private final String[] COLUMNS = {"Equipment ID", "Name", "Location", "Deployed"};
    private Iterable<Equipment> equipment;

    public EquipmentTable() {
        create();
    }

    public JPanel getEquipmentTablePanel(){
        return equipmentTablePanel;
    }

    private void create() {
//        Iterable<Equipment> equipment = equipmentService.findAll();
//        System.out.println(equipment);
        equipmentTable.setModel(DefaultTableModel(

        ));
    }
}
