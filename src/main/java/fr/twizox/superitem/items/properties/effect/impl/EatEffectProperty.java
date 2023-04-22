package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EatEffectProperty extends AbstractEffectProperty<PlayerItemConsumeEvent> {

    private final int cooldown;

    public EatEffectProperty(PotionEffect potionEffect, int cooldown) {
        super(potionEffect);
        this.cooldown = cooldown;
    }

    public EatEffectProperty() {
        this(new PotionEffect(PotionEffectType.SPEED, 200, 1), 300);
    }

    @Override
    public void handle(PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();

        applyEffect(player);
        player.setCooldown(event.getItem().getType(), cooldown);
    }

}
