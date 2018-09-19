package com.github.glasspane.halorings.debug;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.entity.EntityMagicCircleAgeable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = HaloRings.MODID)
public class DebugHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if(event.getSide() == Side.SERVER && event.getFace() == EnumFacing.UP && event.getHitVec() != null) {
            Item item = event.getEntityPlayer().getHeldItem(event.getHand()).getItem();
            if(item == Items.STICK) {
                EntityMagicCircleAgeable circle = new EntityMagicCircleAgeable(event.getWorld(), 0xFFFF0000, 600);
                circle.setPosition(event.getHitVec().x, event.getHitVec().y, event.getHitVec().z);
                event.getWorld().spawnEntity(circle);
                event.setResult(Event.Result.ALLOW);
            }
            else if(item == Items.BONE) {
                EntityMagicCircleAgeable circle = new EntityMagicCircleAgeable(event.getWorld(), 0xFF0000FF, 20);
                circle.setPosition(event.getHitVec().x, event.getHitVec().y, event.getHitVec().z);
                event.getWorld().spawnEntity(circle);
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}
