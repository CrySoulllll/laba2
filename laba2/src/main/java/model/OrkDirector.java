package model;


import model.builderFactories.OrkBuilderFactory;
import model.gearFactories.OrkGearFactory;

public class OrkDirector {

    private final OrkBuilderFactory builderFactory;
    private final OrkGearFactory gearFactory;

    public OrkDirector(OrkBuilderFactory builderFactory, OrkGearFactory gearFactory) {
        this.builderFactory = builderFactory;
        this.gearFactory = gearFactory;
    }

    public Ork createBasicOrk() {
        return builderFactory.createOrkBuilder()
                .type(Ork.OrkType.BASIC)
                .build();
    }

    public Ork createLeaderOrk() {
        return builderFactory.createOrkBuilder()
                .type(Ork.OrkType.COMMANDER)
                .banner(gearFactory.createBanner() + " и горн")
                .build();
    }

    public Ork createScoutOrk() {
        return builderFactory.createOrkBuilder()
                .type(Ork.OrkType.SCOUT)
                .weapon("Лук")
                .build();
    }
}
