package fr.twizox.items.items.properties.farm;

import fr.twizox.items.SuperItems;
import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class HarvestProperty implements ItemProperty<BlockBreakEvent> {

    private final List<Material> crops = List.of(Material.CARROTS, Material.POTATOES, Material.WHEAT, Material.BEETROOTS, Material.NETHER_WART);

    @Override
    public void handle(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Block block = event.getBlock();
        player.sendMessage("HarvestProperty: " + block.getType().toString());
        if(!crops.contains(block.getType())) return;

        replantCrop(block.getLocation(), block.getType());
    }

    private void replantCrop(Location location, Material material) {
        Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(SuperItems.class), () -> {
            location.getBlock().setType(material);
        }, 20L);
    }

}
