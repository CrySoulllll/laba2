package model.gearFactories;

public class MistyMountainsGearFactory implements OrkGearFactory {
    @Override
    public String createWeapon() {
        return "Топор";
    }
    @Override 
    public String createArmor() {
        return "Кожаная броня";
    }
    @Override 
    public String createBanner() {
        return "Знамя с Луной";
    }
}
