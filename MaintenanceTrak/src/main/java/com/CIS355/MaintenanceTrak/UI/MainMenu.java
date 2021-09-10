package com.CIS355.MaintenanceTrak.UI;

import com.CIS355.MaintenanceTrak.UI.Tables.Equipment.EquipmentTable;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() throws HeadlessException {
        setTitle("Maintenance Trak");
        add(new EquipmentTable());
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
