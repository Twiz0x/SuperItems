package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.items.properties.ItemProperty;
import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

/**
 * @author Salers
 * made on fr.twizox.superitem.items.properties.effect.impl
 */
public class ClickEffectProperty extends AbstractEffectProperty<PlayerInteractEvent> {

    public ClickEffectProperty(PotionEffect potionEffect) {
        super(potionEffect);
    }

    @Override
    public void handle(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if(event.getAction() == Action.PHYSICAL) return;

        applyEffect(player);

        // TODO: Cooldown or item remove
    }

    @Override
    public ItemProperty<PlayerInteractEvent> deserialize(ConfigurationSection section) {
        return new ClickEffectProperty(getPotionEffect(section));
    }
}
