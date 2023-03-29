package fr.twizox.items.items.properties.effect.impl;

import fr.twizox.items.items.properties.ItemProperty;
import fr.twizox.items.items.properties.effect.AbstractEffectProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffectType;

public class HeldItemEffectProperty extends AbstractEffectProperty implements ItemProperty<PlayerItemHeldEvent> {

    public HeldItemEffectProperty(PotionEffectType type, int amplifier) {
        super(type, 20, amplifier);
    }

    @Override
    public void handle(PlayerItemHeldEvent event) {
        final Player player = event.getPlayer();

        if (!checkItem(player)) return;

        applyEffect(player);

    }

    @Override
    public boolean handleItemInInventory() {
        return true;
    }
}
