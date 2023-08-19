package cn.allay.api.entity.attribute;

import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
public enum AttributeType {

    //Base Entity
    ABSORPTION("minecraft:absorption", 0, Float.MAX_VALUE, 0),
    KNOCKBACK_RESISTENCE("minecraft:knockback_resistance", 0, 1, 0),
    HEALTH("minecraft:health", 0, Float.MAX_VALUE, 20),
    MOVEMENT("minecraft:movement", 0.1f, 1, 0.1f),
    FOLLOW_RANGE("minecraft:follow_range", 0, 2048, 16),
    ATTACK_DAMAGE("minecraft:attack_damage", 1, Float.MAX_VALUE, 1),
    LUCK("minecraft:luck", -1024, 1024, 0),
    FALL_DAMAGE("minecraft:fall_damage", 0, Float.MAX_VALUE, 1),

    //Horse
    HORSE_JUMP_STRENGTH("minecraft:horse.jump_strength", 0, 20, 0.7f),

    //Zombie
    ZOMBIE_SPAWN_REINFORCEMENTS("minecraft:zombie.spawn_reinforcements", 0, 1, 0),

    //Player
    PLAYER_HUNGER("minecraft:player.hunger", 0, 20, 20),
    PLAYER_SATURATION("minecraft:player.saturation", 0, 20, 20),
    PLAYER_EXHAUSTION("minecraft:player.exhaustion", 0, 5, 0.41f),
    PLAYER_LEVEL("minecraft:player.level", 0, 24791, 0),
    PLAYER_EXPERIENCE("minecraft:player.experience", 0, 1, 0);

    private final String key;
    private final float minValue;
    private final float maxValue;
    private final float defaultValue;

    AttributeType(String key, float minValue, float maxValue, float defaultValue) {
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
    }

    public Attribute newAttributeInstance() {
        return new Attribute(this.key, this.minValue, this.maxValue, this.defaultValue, this.defaultValue);
    }

    public static Attribute fromNBT(NbtMap nbt) {
        return new Attribute(
                nbt.getString("Name"),
                nbt.getFloat("Min"),
                nbt.getFloat("Max"),
                nbt.getFloat("Current"),
                nbt.getFloat("Base")
        );
    }
}
