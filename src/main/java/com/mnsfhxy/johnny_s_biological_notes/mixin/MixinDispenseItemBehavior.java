package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenseItemBehavior.class)
public class MixinDispenseItemBehavior {
    @Inject(method = "bootStrap", at = @At("TAIL"))
    private static void bootStrap(CallbackInfo ci){
        DispenseItemBehavior myDispenseitembehavior = new OptionalDispenseItemBehavior() {
            /**
             * Dispense the specified stack, play the dispense sound and spawn particles.
             */
            protected ItemStack execute(BlockSource p_123429_, ItemStack p_123430_) {
                this.setSuccess(ArmorItem.dispenseArmor(p_123429_, p_123430_));
                return p_123430_;
            }
        };
        DispenserBlock.registerBehavior(RegistrationInit.BLOCK_ITEM_TRIDACNA_SHELL.get(), myDispenseitembehavior);

    }
}
