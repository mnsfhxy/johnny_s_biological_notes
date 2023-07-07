package com.mnsfhxy.johnny_s_biological_notes.capability.spirit;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class PlayerSpiritProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerSpirit> PLAYER_SPIRIT = CapabilityManager.get(new CapabilityToken<PlayerSpirit>() {
    });
    private PlayerSpirit playerSpirit = null;
    private final LazyOptional<PlayerSpirit> optional = LazyOptional.of(this::createPlayerSpirit);


    @Nonnull
    private PlayerSpirit createPlayerSpirit() {
        if (playerSpirit == null) {
            playerSpirit = new PlayerSpirit();
        }
        return playerSpirit;
    }

    @Override

    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap==PLAYER_SPIRIT){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt=new CompoundTag();
        createPlayerSpirit().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSpirit().loadNBTData(nbt);
    }

}
