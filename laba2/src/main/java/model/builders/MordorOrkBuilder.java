package model.builders;

import model.Ork;

public class MordorOrkBuilder extends OrkBuilder {
    public MordorOrkBuilder() {
        super(Ork.Tribe.MORDOR);
    }

    @Override
    protected void generateBaseAttributes() {
        strength = random.nextInt(71) + 30;
        agility = random.nextInt(50) + 1;
        intelligence = random.nextInt(30) + 1;
        health = random.nextInt(151) + 50;
    }
}
