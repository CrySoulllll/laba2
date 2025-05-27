package controller;

import model.gearFactories.MistyMountainsGearFactory;
import model.gearFactories.DolGuldurGearFactory;
import model.gearFactories.OrkGearFactory;
import model.gearFactories.GreyMountainsGearFactory;
import model.gearFactories.MordorGearFactory;
import model.builderFactories.DolGuldurOrkBuilderFactory;
import model.builderFactories.MistyMountainsOrkBuilderFactory;
import model.builderFactories.OrkBuilderFactory;
import model.builderFactories.GreyMountainsOrkBuilderFactory;
import model.builderFactories.MordorOrkBuilderFactory;
import model.Ork;
import model.Ork.Tribe;
import model.OrkDirector;
import view.MainView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

public class ArmyController {
     private final Map<String, Ork> orks = new HashMap<>();
    private final Map<Tribe, OrkBuilderFactory> builderFactories;
    private final Map<Tribe, OrkGearFactory> gearFactories;
    private MainView view;

    public ArmyController() {
        this.builderFactories = initializeBuilderFactories();
        this.gearFactories = initializeGearFactories();
    }

    public void launchApplication() {
        SwingUtilities.invokeLater(() -> {
            this.view = new MainView(this);
            view.setVisible(true);
        });
    }

    private Map<Tribe, OrkBuilderFactory> initializeBuilderFactories() {
        return Map.of(
            Tribe.MORDOR, new MordorOrkBuilderFactory(),
            Tribe.DOL_GULDUR, new DolGuldurOrkBuilderFactory(),
            Tribe.MISTY_MOUNTAINS, new MistyMountainsOrkBuilderFactory(),
            Tribe.GREY_MOUNTAINS, new GreyMountainsOrkBuilderFactory()
        );
    }

    private Map<Tribe, OrkGearFactory> initializeGearFactories() {
        return Map.of(
            Tribe.MORDOR, new MordorGearFactory(),
            Tribe.DOL_GULDUR, new DolGuldurGearFactory(),
            Tribe.MISTY_MOUNTAINS, new MistyMountainsGearFactory(),
            Tribe.GREY_MOUNTAINS, new GreyMountainsGearFactory()
        );
    }
    
    public void createOrk(Ork.Tribe tribe, Ork.OrkType type) {
        OrkBuilderFactory builderFactory = builderFactories.get(tribe);
        OrkGearFactory gearFactory = gearFactories.get(tribe);
        OrkDirector director = new OrkDirector(builderFactory, gearFactory);
        Ork ork = switch (type) {
            case COMMANDER ->
                director.createLeaderOrk();
            case SCOUT ->
                director.createScoutOrk();
            case BASIC ->
                director.createBasicOrk();
        };

        orks.put(ork.getName(), ork);
        view.addOrkToDisplay(ork);
    }

    public Ork getOrkByName(String name) {
        return orks.get(name);
    }

    public List<Ork> getOrksByTribe(Tribe tribe) {
        return orks.values().stream()
                .filter(ork -> ork.getTribe() == tribe)
                .toList();
    }

}
