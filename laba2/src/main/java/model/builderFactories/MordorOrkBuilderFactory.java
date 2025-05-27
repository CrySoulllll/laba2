package model.builderFactories;

import model.builders.OrkBuilder;
import model.builders.MordorOrkBuilder;
import model.gearFactories.MordorGearFactory;
import model.gearFactories.OrkGearFactory;


public class MordorOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new MordorGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new MordorOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
