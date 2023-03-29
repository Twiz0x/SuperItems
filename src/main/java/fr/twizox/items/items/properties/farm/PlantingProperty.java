package fr.twizox.items.items.properties.farm;

import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

public class PlantingProperty implements ItemProperty<PlayerInteractEvent> {

    private final int radius;

    public PlantingProperty(int radius) {
        this.radius = radius;
    }

    @Override
    public void handle(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock == null || clickedBlock.getType() != Material.FARMLAND) return;
        ItemStack[] inventoryContents = player.getInventory().getContents();

        for (int i = 0; i < inventoryContents.length; i++) {
            ItemStack item = inventoryContents[i];
            if (item == null || !isPlantable(item.getType())) return;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    Block relativeBlock = clickedBlock.getRelative(x, 1, z);
                    if (relativeBlock.getType() == Material.AIR && relativeBlock.getRelative(0, -1, 0).getType() == Material.FARMLAND) {
                        relativeBlock.setType(item.getType());
                        item.setAmount(item.getAmount() - 1);
                        return;
                    }
                }
            }
        }

    }

    private static boolean isPlantable(Material type) {
        return type.getData().isInstance(Crops.class);
    }

}
