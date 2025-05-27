package model.builderFactories;

import model.builders.OrkBuilder;
import model.builders.MistyMountainsOrkBuilder;
import model.gearFactories.MistyMountainsGearFactory;
import model.gearFactories.OrkGearFactory;

public class MistyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new MistyMountainsGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new MistyMountainsOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
    
