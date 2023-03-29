package fr.twizox.items.items.properties.effect.impl;

import fr.twizox.items.items.properties.ItemProperty;
import fr.twizox.items.items.properties.effect.AbstractEffectProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Salers
 * made on fr.twizox.items.items.properties.effect.impl
 */
public class ClickEffectProperty extends AbstractEffectProperty implements ItemProperty<PlayerInteractEvent> {

    public ClickEffectProperty(PotionEffectType type, int amplifier, int duration) {
        super(type, amplifier, duration);
    }

    @Override
    public void handle(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if(event.getAction() == Action.PHYSICAL || !checkItem(player)) return;

        applyEffect(player);
    }
}
