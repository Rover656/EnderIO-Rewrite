package com.enderio.base.common.init;

import com.enderio.EnderIO;
import com.enderio.api.capability.IDarkSteelUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.EmpoweredUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.ForkUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.SpoonUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.direct.DirectUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.explosive.ExplosivePenetrationUpgrade;
import com.enderio.base.common.item.darksteel.upgrades.explosive.ExplosiveUpgrade;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * Work in progress, for now, don't instantiate this.
 */
@Deprecated()
public class EIODarkSteelUpgrades {
    private static final DeferredRegister<IDarkSteelUpgrade> UPGRADES = DeferredRegister.create(EnderIO.loc("dark_steel_upgrades"), EnderIO.MODID);
    public static final RegistryObject<EmpoweredUpgrade> EMPOWERED_UPGRADE = UPGRADES.register("empowered", EmpoweredUpgrade::new);
    public static final RegistryObject<SpoonUpgrade> SPOON_UPGRADE = UPGRADES.register("spoon", SpoonUpgrade::new);
    public static final RegistryObject<ForkUpgrade> FORK_UPGRADE = UPGRADES.register("fork", ForkUpgrade::new);
    public static final RegistryObject<DirectUpgrade> DIRECT_UPGRADE = UPGRADES.register("direct", DirectUpgrade::new);
    public static final RegistryObject<ExplosiveUpgrade> EXPLOSIVE_UPGRADE = UPGRADES.register("explosive", ExplosiveUpgrade::new);
    public static final RegistryObject<ExplosivePenetrationUpgrade> EXPLOSIVE_PENETRATION_UPGRADE = UPGRADES.register("explosive_penetration", ExplosivePenetrationUpgrade::new);

    public static final Supplier<IForgeRegistry<IDarkSteelUpgrade>> UPGRADES_REGISTRY = UPGRADES.makeRegistry(() -> new RegistryBuilder<IDarkSteelUpgrade>()); // TODO: Should saving be disabled?

    public static void register() {
        UPGRADES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
