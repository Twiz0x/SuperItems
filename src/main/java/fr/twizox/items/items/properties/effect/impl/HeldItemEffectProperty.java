package fr.twizox.items.items.properties.effect.impl;

import fr.twizox.items.items.properties.ItemProperty;
import fr.twizox.items.items.properties.effect.AbstractEffectProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeldItemEffectProperty extends AbstractEffectProperty<PlayerItemHeldEvent> {

    public HeldItemEffectProperty(PotionEffectType type, int amplifier) {
        super(new PotionEffect(type, Integer.MAX_VALUE, amplifier));
    }

    public HeldItemEffectProperty(PotionEffect potionEffect) {
        super(potionEffect);
        if (potionEffect.getDuration() != Integer.MAX_VALUE) throw new IllegalArgumentException("Duration in hand must be Integer.MAX_VALUE");
    }

    @Override
    public void handle(PlayerItemHeldEvent event) {
        final Player player = event.getPlayer();

        if (!checkItem(player)) return;

        applyEffect(player);

    }

    @Override
    public boolean handleWhenItemHoldOnly() {
        return true;
    }

    @Override
    public ItemProperty<PlayerItemHeldEvent> deserialize(ConfigurationSection section) {
        return new HeldItemEffectProperty(getPotionEffect(section));
    }

}
