package me.cadox8.riveth.kits;

import lombok.Getter;
import me.cadox8.riveth.Riveth;
import me.cadox8.riveth.api.RUser;
import me.cadox8.riveth.utils.ItemUtils;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Kit {

    public static List<Kit> kits = new ArrayList<>();

    public static final Kit DEV_KIT = new Kit("Dev kit",
            Arrays.asList(ItemUtils.createPlayerHead("cadox8's Head", UUID.fromString("c56cace3-5701-4754-8c15-948f64e4978e"), Arrays.asList("&cRiveth's Developer"))));

    @Getter private String name;
    @Getter private List<ItemStack> items;
    @Getter private String perm;

    public Kit() {}

    /**
     * Create custom kits from plugins
     * */
    public Kit(String name, List<ItemStack> items) {
        this.name = name;
        this.items = items;

        this.perm = "riveth.kit." + name.replace(" ", "_");

        kits.add(this);
    }

    public void loadFromConfig(String key) {
        this.name = "";
        items = new ArrayList<>();
        this.perm = "riveth.kit." + name.replace(" ", "_");

        //ToDO: get all items from config and add them

        kits.add(this);
    }

    public void preview(RUser u) {
        if (!u.hasPermission(getPerm())) {
            u.sendMessage("&cYou don't have permissions to see this kit");
            return;
        }
        int maxInventory = 36;
        Inventory inv = Riveth.getInstance().getServer().createInventory(null, maxInventory, name);

        for (int x = 0; x <= maxInventory; x++) inv.setItem(x, items.get(x));

        u.getPlayer().closeInventory();
        u.getPlayer().openInventory(inv);
    }

    public void give(RUser u) {
        if (!u.hasPermission(getPerm())) {
            u.sendMessage("&cYou don't have permissions to get this kit");
            return;
        }
        if (u.getPlayer().getInventory().getStorageContents().length < items.size()) {
            u.sendMessage("&cYou don't have space to receive this kit");
            return;
        }
        u.getPlayer().getInventory().addItem(items.toArray(new ItemStack[items.size()]));
        u.sendMessage("&6Kit &c" + getName() + " &6received");
    }
}
