package com.github.glasspane.halorings.client;

import net.minecraft.client.Minecraft;

//FIXME key handling!
//@Mod.EventBusSubscriber(modid = HaloRings.MODID, value = Side.CLIENT)
public class KeyHandler {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static long lastPacket = 0L;
    private static boolean lastTickJump = false;

//    @SubscribeEvent
//    public static void onClientTick(TickEvent.ClientTickEvent event) {
//        if(event.phase == TickEvent.Phase.START) {
//            if(mc.player != null && mc.player.isElytraFlying() && mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemPowerElytra) {
//                if(mc.gameSettings.keyBindJump.isKeyDown()) {
//                    long currentTime = System.nanoTime() / 1000000L;
//                    if(currentTime - PacketElytraBoostRocket.NETWORK_TIMEOUT >= lastPacket) {
//                        NetworkHandler.INSTANCE.sendToServer(new PacketElytraBoostRocket());
//                        lastPacket = currentTime;
//                    }
//                }
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void onPressKey(InputUpdateEvent event) {
//        EntityPlayer player = event.getEntityPlayer();
//        if(!lastTickJump && event.getMovementInput().jump && !player.isElytraFlying() && !player.onGround && !player.isRiding() && !player.capabilities.isFlying && !player.capabilities.allowFlying) {
//            ItemStack stack = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
//            if(stack.getItem() instanceof ItemPowerElytra && ((ItemPowerElytra) stack.getItem()).isUsableElytra(mc.player, stack)) NetworkHandler.INSTANCE.sendToServer(new PacketElytraStartFlying());
//        }
//        lastTickJump = event.getMovementInput().jump;
//    }
}
