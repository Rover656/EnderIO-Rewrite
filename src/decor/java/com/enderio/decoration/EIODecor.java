package com.enderio.decoration;

import com.enderio.EnderIO;
import com.enderio.decoration.common.init.DecorBlockEntities;
import com.enderio.decoration.common.init.DecorBlocks;
import com.enderio.decoration.common.init.DecorEntities;
import com.enderio.decoration.common.network.EnderDecorNetwork;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(modid = EnderIO.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EIODecor {
    @SubscribeEvent
    public static void onConstruct(FMLConstructModEvent event) {
        // TODO: 1.19: This module needs a complete review as it was ignored on first pass.
        // Perform classloads for everything so things are registered.
        DecorBlocks.classload();
        DecorBlockEntities.classload();
        DecorEntities.classload();
        EnderDecorNetwork.register();
    }

    // GatherDataEvent too!
}
