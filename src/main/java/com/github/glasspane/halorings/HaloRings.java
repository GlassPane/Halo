package com.github.glasspane.halorings;

import com.github.upcraftlp.glasspane.api.proxy.IProxy;
import com.github.upcraftlp.glasspane.util.ModUpdateHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.glasspane.halorings.HaloRings.*;

@SuppressWarnings("WeakerAccess")
@Mod(
        certificateFingerprint = FINGERPRINT_KEY,
        name = MODNAME,
        version = VERSION,
        acceptedMinecraftVersions = MCVERSIONS,
        modid = MODID,
        dependencies = DEPENDENCIES,
        updateJSON = UPDATE_JSON
)
public class HaloRings {

    //Version
    public static final String MCVERSIONS = "[1.12.2, 1.13)";
    public static final String VERSION = "@VERSION@";

    //Meta Information
    public static final String MODNAME = "Halo Rings";
    public static final String MODID = "halo_rings";
    public static final String DEPENDENCIES = "required-after:glasspane;required-after:forge";
    public static final String UPDATE_JSON = "@UPDATE_JSON@";

    public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";

    private static final Logger log = LogManager.getLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID + ".name") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.APPLE); //TODO change once we have items!
        }
    };

    @SidedProxy(clientSide = "com.github.glasspane.halorings.proxy.ClientProxy", serverSide = "com.github.glasspane.halorings.proxy.ServerProxy")
    public static IProxy proxy = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModUpdateHandler.registerMod(MODID);
        proxy.preInit(event);
    }
}
