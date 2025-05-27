package model.gearFactories;

public class GreyMountainsGearFactory implements OrkGearFactory {
    @Override 
    public String createWeapon() {
        return "Длинный меч";
    }
    @Override 
    public String createArmor() {
        return "Чешуйчатая броня";
    }
    @Override 
    public String createBanner() {
        return "Знамя с Драконом";
    }
}
