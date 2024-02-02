package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.EntityYoungBeluga;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 白鲸结群游泳的Goal
 */
public class BelugaGroupSwimGoal extends Goal {
    private final EntityYoungBeluga beluga;
    private int detectScope;
    private int maxDistance;
    private Optional<EntityBeluga> targetBeluga;

    public BelugaGroupSwimGoal(EntityYoungBeluga beluga, int detectScope, int maxDistance) {
        this.beluga = beluga;
        setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));

        this.detectScope = detectScope;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean canUse() {
        targetBeluga = closestBelugaIn(detectScope);
        return targetBeluga.isPresent();
    }

    @Override
    public void tick() {
        super.tick();

        this.beluga.getLookControl().setLookAt(targetBeluga.get());
        this.beluga.getNavigation().moveTo(targetBeluga.get(), 1.0);
    }

    @Override
    public boolean canContinueToUse() {
        return isFarawayFrom(targetBeluga.get());
    }

    /**
     * 查找当前白鲸指定范围内的，和当前白鲸距离大于最小距离的，
     * 距离当前白鲸最近的白鲸
     * @param scope
     * @return
     */
    private Optional<EntityBeluga> closestBelugaIn(int scope) {
        Predicate<Entity> predicate = (entity) ->
                entity instanceof EntityBeluga
                && isFarawayFrom(entity);
        Optional<EntityBeluga> closestBeluga = this.beluga.level.getEntities(this.beluga, this.beluga.getBoundingBox().inflate(scope), predicate)
                .stream()
                .min(Comparator.comparingDouble(
                        entity -> this.beluga.distanceTo(entity)
                )).map(entity -> (EntityBeluga) entity);

        return closestBeluga;
    }

    /**
     * 当前白鲸距离目标是否远
     * @param entity
     * @return
     */
    private boolean isFarawayFrom(Entity entity) {
        return this.beluga.distanceTo(entity) > maxDistance;
    }
}
