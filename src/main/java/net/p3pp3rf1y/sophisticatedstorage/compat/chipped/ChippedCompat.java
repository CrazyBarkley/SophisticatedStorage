package net.p3pp3rf1y.sophisticatedstorage.compat.chipped;

import earth.terrarium.chipped.common.registry.ModRecipeTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.UpgradeGuiManager;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerRegistry;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.compat.ICompat;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeTab;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedstorage.client.gui.StorageButtonDefinitions;
import net.p3pp3rf1y.sophisticatedstorage.init.ModItems;

public class ChippedCompat implements ICompat {

	public static final RegistryObject<BlockTransformationUpgradeItem> BOTANIST_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/botanist_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.BOTANIST_WORKBENCH_TYPE));
	public static final RegistryObject<BlockTransformationUpgradeItem> GLASSBLOWER_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/glassblower_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.GLASSBLOWER_TYPE));
	public static final RegistryObject<BlockTransformationUpgradeItem> CARPENTER_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/carpenter_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.CARPENTERS_TABLE_TYPE));
	public static final RegistryObject<BlockTransformationUpgradeItem> SHEPHERD_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/shepherd_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.LOOM_TABLE_TYPE));
	public static final RegistryObject<BlockTransformationUpgradeItem> MASON_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/mason_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.MASON_TABLE_TYPE));
	public static final RegistryObject<BlockTransformationUpgradeItem> PHILOSOPHER_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/philosopher_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.ALCHEMY_BENCH_TYPE));
	public static final RegistryObject<BlockTransformationUpgradeItem> TINKERER_WORKBENCH_UPGRADE = ModItems.ITEMS.register("chipped/tinkerer_workbench_upgrade",
			() -> new BlockTransformationUpgradeItem(ModRecipeTypes.TINKERING_TABLE_TYPE));

	@Override
	public void init() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.addListener(this::registerContainers);
	}

	public void registerContainers(RegisterEvent event) {
		if (!event.getRegistryKey().equals(ForgeRegistries.Keys.MENU_TYPES)) {
			return;
		}
		registerUpgradeContainer(BOTANIST_WORKBENCH_UPGRADE);
		registerUpgradeContainer(GLASSBLOWER_WORKBENCH_UPGRADE);
		registerUpgradeContainer(CARPENTER_WORKBENCH_UPGRADE);
		registerUpgradeContainer(SHEPHERD_WORKBENCH_UPGRADE);
		registerUpgradeContainer(MASON_WORKBENCH_UPGRADE);
		registerUpgradeContainer(PHILOSOPHER_WORKBENCH_UPGRADE);
		registerUpgradeContainer(TINKERER_WORKBENCH_UPGRADE);
	}

	private void registerUpgradeContainer(RegistryObject<BlockTransformationUpgradeItem> item) {
		UpgradeContainerType<BlockTransformationUpgradeWrapper, BlockTransformationUpgradeContainer> containerType = new UpgradeContainerType<>(BlockTransformationUpgradeContainer::new);
		UpgradeContainerRegistry.register(item.getId(), containerType);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> UpgradeGuiManager.registerTab(containerType, (BlockTransformationUpgradeContainer upgradeContainer, Position position, StorageScreenBase<?> screen) -> {
			String itemName = item.getId().getPath();
			return new BlockTransformationUpgradeTab(upgradeContainer, position, screen, StorageButtonDefinitions.SHIFT_CLICK_TARGET, itemName.replace('/', '_').substring(0, itemName.length() - "_upgrade".length()));
		}));
	}

	@Override
	public void setup() {
		//noop
	}
}
