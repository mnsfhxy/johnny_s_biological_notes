package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message.BelugaSongParticlePacket;
import com.mnsfhxy.johnny_s_biological_notes.init.NetworkInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.tool.CooldownMonitor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;

/**
 * 白鲸的鸣奏Goal，检查附近40格范围内有正在鸣奏的音符盒或唱片机则靠近
 */
public class BelugaSongPlayGoal extends Goal {
    private static final int COOLDOWN_INTERVAL = Config.getInstance().intValueOf("entity.beluga.songPlay.cooldownInterval");
    private final EntityBeluga beluga;
    private Optional<BlockPos> focusedBlockPos;
    private int scope;
    private int startTick;
    private int particleInterval;
    private int particleNumber;
    private CooldownMonitor cooldownMonitor;

    public BelugaSongPlayGoal(EntityBeluga beluga, int scope, int particleInterval, int particleNumber) {
        this.beluga = beluga;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.scope = scope;
        this.particleInterval = particleInterval;
        this.particleNumber = particleNumber;
        this.cooldownMonitor = new CooldownMonitor(COOLDOWN_INTERVAL);
    }

    @Override
    public boolean canUse() {
        if (!this.cooldownMonitor.checkIsActive())
            return false;

        BlockPos belugaPos = beluga.blockPosition();

        focusedBlockPos = closestPlayingMusicBoxPosNearby(belugaPos);

        return focusedBlockPos.isPresent();
    }

    @Override
    public void start() {
        this.startTick = this.beluga.tickCount;
        this.beluga.playSound(SoundInit.BELUGA_SING.get(), 1.0f, 1.0f);
    }

    @Override
    public void tick() {
        beluga.getNavigation().moveTo(focusedBlockPos.get().getX(), focusedBlockPos.get().getY(), focusedBlockPos.get().getZ(), 1.0D);

        if (runTime() %
                particleInterval == 0)
            NetworkInit.NETWORK.send(PacketDistributor.ALL.noArg(), new BelugaSongParticlePacket(this.beluga.getId(), songPitchOf(focusedBlockPos.get()), particleNumber));
    }

    @Override
    public boolean canContinueToUse() {
        return isJukeboxPlaying(stateOf(focusedBlockPos.get()));
    }

    @Override
    public void stop() {
        this.cooldownMonitor.reset();
    }

    /**
     * 计算当前Goal执行的时间
     * @return
     */
    private int runTime() {
        return this.beluga.tickCount - this.startTick;
    }

    /**
     * 直接返回随机的音高，因为目前是唱片机
     * @param playingBlockPos
     * @return
     */
    private int songPitchOf(BlockPos playingBlockPos) {
        Random random = new Random();
        return random.nextInt(25);  // 返回一个0到24的随机整数
    }

    /**
     * 返回音符盒的当前音高
     * @param state
     * @return
     */
    private int songPitchInNoteBlockWith(BlockState state) {
        return state.getValue(NoteBlock.NOTE);
    }

    /**
     * 返回唱片机的当前音高
     * 因为无法获取唱片机的实时音高，所以目前实现为返回随机音高
     * @param state
     * @return
     */
    private int songPitchInJukeboxBlockWith(BlockState state) {
        Random random = new Random();
        return random.nextInt(25);  // 返回一个0到24的随机整数
    }

    /**
     * 遍历查找最近的正在播放的音符盒或唱片机
     *
     * @param belugaPos
     * @return
     */
    private Optional<BlockPos> closestPlayingMusicBoxPosNearby(BlockPos belugaPos) {
        for (BlockPos pos : nearbyBlockPoses(belugaPos)) {
            BlockState blockState = stateOf(pos);
            if (isJukebox(blockState) && isJukeboxPlaying(blockState)) {
                return Optional.of(pos);
            }
        }

        return Optional.empty();
    }

    /**
     * 附近的BlockPos的集合
     *
     * @param belugaPos
     * @return
     */
    @NotNull
    private Iterable<BlockPos> nearbyBlockPoses(BlockPos belugaPos) {
        return BlockPos.betweenClosed(belugaPos.offset(-scope, -scope, -scope), belugaPos.offset(scope, scope, scope));
    }

    /**
     * 当前方块是否为唱片机
     *
     * @param state
     * @return
     */
    private boolean isJukebox(BlockState state) {
        return state.getBlock() instanceof JukeboxBlock;
    }

    /**
     * 唱片机是否正在播放
     *
     * @param state
     * @return
     */
    @NotNull
    private Boolean isJukeboxPlaying(BlockState state) {
        return state.getValue(JukeboxBlock.HAS_RECORD);
    }

    /**
     * 当前方块是否为音符盒
     *
     * @param pos
     * @return
     */
    private boolean isNote(BlockPos pos) {
        return stateOf(pos).getBlock() instanceof NoteBlock;
    }

    /**
     * 音符盒是否正在播放
     *
     * @param pos
     * @return
     */
    @NotNull
    private Boolean isNotePlaying(BlockPos pos) {
        return stateOf(pos).getValue(NoteBlock.POWERED);
    }

    /**
     * 获取方块的状态
     *
     * @param pos
     * @return
     */
    @NotNull
    private BlockState stateOf(BlockPos pos) {
        return beluga.level.getBlockState(pos);
    }
}
