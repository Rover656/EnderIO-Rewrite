package com.enderio.base.common.item.darksteel.upgrades.data;

import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * TODO: Implement
 * This will load in JSON files defined within the data/<modid>/dark_steel_upgrades/... directory
 * The name of the file will correspond with the item it refers to.
 * Within will be a list of valid upgrades for said item.
 * This will be used to determine if an upgrade is valid for a given tool.
 */
public class ToolUpgradeReloadListener implements PreparableReloadListener {
    @Override
    public CompletableFuture<Void> reload(PreparationBarrier pPreparationBarrier, ResourceManager pResourceManager, ProfilerFiller pPreparationsProfiler,
        ProfilerFiller pReloadProfiler, Executor pBackgroundExecutor, Executor pGameExecutor) {
        return null;
    }
}
