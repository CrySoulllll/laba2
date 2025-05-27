package model.builderFactories;

import model.builders.OrkBuilder;
import model.builders.DolGuldurOrkBuilder;
import model.gearFactories.DolGuldurGearFactory;
import model.gearFactories.OrkGearFactory;


public class DolGuldurOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new DolGuldurGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new DolGuldurOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
