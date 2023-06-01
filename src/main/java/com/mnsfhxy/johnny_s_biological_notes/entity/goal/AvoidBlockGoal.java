package com.mnsfhxy.johnny_s_biological_notes.entity.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AvoidBlockGoal  extends Goal {
    protected final PathfinderMob mob;
    private final double walkSpeedModifier;
    private final double sprintSpeedModifier;
    @Nullable
    protected BlockPos toAvoid;
    protected final float maxDist;
    @Nullable
    protected Path path;
    protected final PathNavigation pathNav;
    /** Class of entity this behavior seeks to avoid */
    protected final Class<Block> avoidClass;
    protected final Predicate<Block> avoidPredicate;

    /**
     * Goal that helps mobs avoid mobs of a specific class
     */

    /**
     * Goal that helps mobs avoid mobs of a specific class
     */
    public AvoidBlockGoal(PathfinderMob pMob, Class<Block> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier, Predicate<Block> pPredicateAvoidBlock) {
        this.mob = pMob;
        this.avoidClass = pEntityClassToAvoid;
        this.avoidPredicate = pPredicateAvoidBlock;
        this.maxDist = pMaxDistance;
        this.walkSpeedModifier = pWalkSpeedModifier;
        this.sprintSpeedModifier = pSprintSpeedModifier;
        this.pathNav = pMob.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Goal that helps mobs avoid mobs of a specific class
     */

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        AABB inflate = this.mob.getBoundingBox().inflate((double) this.maxDist, 3.0D, (double) this.maxDist);
        Iterator<BlockPos> iterator = BlockPos.betweenClosedStream(inflate).iterator();
        while (iterator.hasNext()) {
            var p=iterator.next();
            if(avoidPredicate.test(this.mob.level.getBlockState(p).getBlock())){
                //需要避免
                if(this.toAvoid==null)this.toAvoid=p;
                if(this.mob.distanceToSqr(p.getX(),p.getY(),p.getZ())<this.mob.distanceToSqr(toAvoid.getX(),toAvoid.getY(),toAvoid.getZ()))this.toAvoid=p;
            }
        }
        if (this.toAvoid == null) {
            return false;
        } else {
            Vec3 bPos = new Vec3(toAvoid.getX(), toAvoid.getY(), toAvoid.getZ());
            Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 16, 7, bPos);
            if (vec3 == null) {
                return false;
            } else {
                this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
                return this.path != null;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return !this.pathNav.isDone();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.pathNav.moveTo(this.path, this.walkSpeedModifier);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.toAvoid = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.mob.distanceToSqr(this.toAvoid.getX(),this.toAvoid.getY(),this.toAvoid.getZ()) < 49.0D) {
            this.mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
        } else {
            this.mob.getNavigation().setSpeedModifier(this.walkSpeedModifier);
        }

    }
}
