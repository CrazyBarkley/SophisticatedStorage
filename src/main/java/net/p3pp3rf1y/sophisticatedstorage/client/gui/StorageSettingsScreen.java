package net.p3pp3rf1y.sophisticatedstorage.client.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SettingsContainer;
import net.p3pp3rf1y.sophisticatedcore.settings.StorageSettingsTabControlBase;
import net.p3pp3rf1y.sophisticatedstorage.SophisticatedStorage;
import net.p3pp3rf1y.sophisticatedstorage.network.OpenStorageInventoryMessage;

public class StorageSettingsScreen extends SettingsScreen {
	public StorageSettingsScreen(SettingsContainer<?> screenContainer, Inventory inv, Component title) {
		super(screenContainer, inv, title);
	}

	@Override
	protected StorageSettingsTabControlBase initializeTabControl() {
		return new StorageSettingsTabControl(this, new Position(leftPos + imageWidth, topPos + 4));
	}

	@Override
	protected void sendStorageInventoryScreenOpenMessage() {
		SophisticatedStorage.PACKET_HANDLER.sendToServer(new OpenStorageInventoryMessage(menu.getBlockPosition()));
	}

	public static StorageSettingsScreen constructScreen(SettingsContainer<?> screenContainer, Inventory inventory, Component title) {
		return new StorageSettingsScreen(screenContainer, inventory, title);
	}
}
