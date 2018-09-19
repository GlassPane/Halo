package com.github.glasspane.halorings.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class ScrollActivatedEvent extends PlayerEvent {

    private final ResourceLocation scroll;

    public ScrollActivatedEvent(EntityPlayer player, ResourceLocation scroll) {
        super(player);
        this.scroll = scroll;
    }

    public ResourceLocation getScroll() {
        return scroll;
    }
}
