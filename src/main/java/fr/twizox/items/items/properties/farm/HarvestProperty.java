package fr.twizox.items.items.properties.farm;

import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class HarvestProperty implements ItemProperty<PlayerInteractEvent> {

    private static final List<Material> crops = List.of(Material.CARROTS, Material.POTATOES, Material.WHEAT, Material.BEETROOTS, Material.NETHER_WART);

    @Override
    public void handle(PlayerInteractEvent event) {

        Block block = event.getClickedBlock();
        if(block == null || !crops.contains(block.getType())) return;

        Ageable ageable = (Ageable) block.getBlockData();
        if(ageable.getAge() != ageable.getMaximumAge()) return;

        Material type = block.getType();

        block.breakNaturally(event.getItem());
        block.setType(type);
    }

    @Override
    public ItemProperty<PlayerInteractEvent> deserialize(ConfigurationSection section) {
        throw new UnsupportedOperationException("Already implemented");
    }
}
