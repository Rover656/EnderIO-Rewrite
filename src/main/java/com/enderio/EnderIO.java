package com.enderio;

import com.enderio.api.integration.IntegrationManager;
import com.enderio.base.common.advancement.UseGliderTrigger;
import com.enderio.base.common.config.BaseConfig;
import com.enderio.base.common.init.*;
import com.enderio.base.common.integrations.EnderIOSelfIntegration;
import com.enderio.base.common.init.EIOCreativeTabs;
import com.enderio.base.common.item.tool.SoulVialItem;
import com.enderio.base.common.lang.EIOLang;
import com.enderio.base.common.tag.EIOTags;
import com.enderio.base.data.EIODataProvider;
import com.enderio.base.data.advancement.EIOAdvancementGenerator;
import com.enderio.base.data.loot.FireCraftingLootTableSubProvider;
import com.enderio.base.data.recipe.*;
import com.enderio.base.data.tags.EIOBlockTagsProvider;
import com.enderio.base.data.tags.EIOFluidTagsProvider;
import com.enderio.core.EnderCore;
import com.enderio.core.common.network.CoreNetwork;
import com.tterrag.registrate.Registrate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(EnderIO.MODID)
public class EnderIO {
    // The Mod ID. This is stored in EnderCore as its the furthest source away but it ensures that it is constant across all source sets.
    public static final String MODID = EnderCore.MODID;

    private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static Registrate registrate() {
        return REGISTRATE.get();
    }

    public EnderIO() {
        // Ensure the enderio config subdirectory is present.
        try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(MODID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register config files
        var ctx = ModLoadingContext.get();
        ctx.registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON_SPEC, "enderio/base-common.toml");
        ctx.registerConfig(ModConfig.Type.CLIENT, BaseConfig.CLIENT_SPEC, "enderio/base-client.toml");

        // Setup core networking now
        CoreNetwork.networkInit();

        // Perform initialization and registration for everything so things are registered.
        EIOCreativeTabs.register();
        EIOItems.register();
        EIOBlocks.register();
        EIOBlockEntities.register();
        EIOFluids.register();
        EIOEnchantments.register();
        EIOTags.register();
        EIOMenus.register();
        EIOPackets.register();
        EIOLang.register();
        EIORecipes.register();
        EIOLootModifiers.register();
        EIOParticles.register();

        // Run datagen after registrate is finished.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(EventPriority.LOWEST, this::onGatherData);
        modEventBus.addListener(SoulVialItem::onCommonSetup);
        IntegrationManager.addIntegration(EnderIOSelfIntegration.INSTANCE);
        new UseGliderTrigger().register();
    }

    public void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        EIODataProvider provider = new EIODataProvider("base");

        provider.addSubProvider(event.includeServer(), new MaterialRecipes(packOutput));
        provider.addSubProvider(event.includeServer(), new BlockRecipes(packOutput));
        provider.addSubProvider(event.includeServer(), new ItemRecipes(packOutput));
        provider.addSubProvider(event.includeServer(), new GrindingBallRecipeProvider(packOutput));
        provider.addSubProvider(event.includeServer(), new GlassRecipes(packOutput));
        provider.addSubProvider(event.includeServer(), new FireCraftingRecipes(packOutput));

//        ForgeBlockTagsProvider b = new ForgeBlockTagsProvider(packOutput, lookupProvider, event.getExistingFileHelper());
//        provider.addSubProvider(event.includeServer(), new EIOItemTagsProvider(packOutput, event.getLookupProvider(), b.contentsGetter(), event.getExistingFileHelper()));
        provider.addSubProvider(event.includeServer(), new EIOFluidTagsProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
        provider.addSubProvider(event.includeServer(), new EIOBlockTagsProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
        provider.addSubProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(), List.of(
            new LootTableProvider.SubProviderEntry(
                FireCraftingLootTableSubProvider::new, LootContextParamSets.EMPTY
            )
        )));

        provider.addSubProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper(),
            List.of(new EIOAdvancementGenerator())));


        generator.addProvider(true, provider);
    }
}
