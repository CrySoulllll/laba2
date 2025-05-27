package model.builderFactories;

import model.builders.OrkBuilder;
import model.builders.GreyMountainsOrkBuilder;
import model.gearFactories.GreyMountainsGearFactory;
import model.gearFactories.OrkGearFactory;

public class GreyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new GreyMountainsGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new GreyMountainsOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
