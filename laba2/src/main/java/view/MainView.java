package view;

import controller.ArmyController;
import model.Ork;
import model.Ork.OrkType;
import model.Ork.Tribe;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainView extends JFrame {
    private final ArmyController controller;
    private final Map<Tribe, DefaultMutableTreeNode> tribeNodes = new HashMap<>();
    private final DefaultTreeModel treeModel;
    private final JTree armyTree;
    private final JPanel infoPanel;

    public MainView(ArmyController controller) {
        super("Армия Саурона");
        this.controller = controller;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Армия Мордора");
        for (Tribe tribe : Tribe.values()) {
            DefaultMutableTreeNode tribeNode = new DefaultMutableTreeNode(tribe.toString());
            tribeNodes.put(tribe, tribeNode);
            root.add(tribeNode);
        }
        
        treeModel = new DefaultTreeModel(root);
        armyTree = new JTree(treeModel);
        armyTree.addTreeSelectionListener(e -> updateInfoPanel());
        
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel controlPanel = createControlPanel();

        add(new JScrollPane(armyTree), BorderLayout.WEST);
        add(new JScrollPane(infoPanel), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JComboBox<Tribe> tribeCombo = new JComboBox<>(Tribe.values());
        JComboBox<OrkType> typeCombo = new JComboBox<>(OrkType.values());
        JButton createButton = new JButton("Создать орка");

        createButton.addActionListener(e -> {
            Tribe tribe = (Tribe) tribeCombo.getSelectedItem();
            OrkType type = (OrkType) typeCombo.getSelectedItem();
            controller.createOrk(tribe, type);
        });

        panel.add(new JLabel("Племя:"));
        panel.add(tribeCombo);
        panel.add(new JLabel("Тип:"));
        panel.add(typeCombo);
        panel.add(createButton);

        return panel;
    }

    public void addOrkToDisplay(Ork ork) {
        DefaultMutableTreeNode orkNode = new DefaultMutableTreeNode(ork.getName());
        tribeNodes.get(ork.getTribe()).add(orkNode);
        treeModel.reload();
        
        TreePath path = new TreePath(orkNode.getPath());
        armyTree.setSelectionPath(path);
        armyTree.scrollPathToVisible(path);
    }

    private void updateInfoPanel() {
        infoPanel.removeAll();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) armyTree.getLastSelectedPathComponent();
        
        if (selectedNode != null && !selectedNode.isRoot()) {
            String orkName = selectedNode.getUserObject().toString();
            Ork ork = controller.getOrkByName(orkName);
            
            if (ork != null) {
                addInfoRow("Имя:", ork.getName());
                addInfoRow("Племя:", ork.getTribe().toString());
                addInfoRow("Тип:", ork.getType().toString());
                addInfoRow("Оружие:", ork.getWeapon());
                addInfoRow("Броня:", ork.getArmor());
                addInfoRow("Знамя:", ork.getBanner() != null ? ork.getBanner() : "нет");
                
                addProgressBar("Сила:", ork.getStrength());
                addProgressBar("Ловкость:", ork.getAgility());
                addProgressBar("Интеллект:", ork.getIntelligence());
                addProgressBar("Здоровье:", ork.getHealth());
            }
        }
        
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void addInfoRow(String label, String value) {
        infoPanel.add(new JLabel(label));
        infoPanel.add(new JLabel(value));
    }

    private void addProgressBar(String label, int value) {
        infoPanel.add(new JLabel(label));
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Math.min(value, 100));
        progressBar.setStringPainted(true);
        infoPanel.add(progressBar);
    }
}