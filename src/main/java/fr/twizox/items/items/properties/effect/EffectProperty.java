package fr.twizox.items.items.properties.effect;

import fr.twizox.items.items.ItemManager;
import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffectType;

public class EffectProperty implements ItemProperty<PlayerItemHeldEvent> {

    private final PotionEffectType potionEffectType;
    private final int amplifier;

    public EffectProperty(PotionEffectType potionEffectType, int amplifier) {
        this.potionEffectType = potionEffectType;
        this.amplifier = amplifier;
    }

    public PotionEffectType getPotionEffectType() {
        return potionEffectType;
    }

    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public void handle(PlayerItemHeldEvent event) {
        ItemManager itemManager = ItemManager.INSTANCE;
        Player player = event.getPlayer();

    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EffectProperty && ((EffectProperty) obj).getPotionEffectType().equals(this.potionEffectType);
    }

}
