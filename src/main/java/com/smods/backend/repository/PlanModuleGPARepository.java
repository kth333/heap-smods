package com.smods.backend.repository;

import com.smods.backend.model.Module;
import com.smods.backend.model.PlanModuleGPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smods.backend.model.composite_key.PlanModuleGPAKey;
import org.springframework.lang.NonNull;

import java.util.List;

@Repository
public interface PlanModuleGPARepository extends JpaRepository<PlanModuleGPA, PlanModuleGPAKey> {

    boolean existsById(@NonNull PlanModuleGPAKey id);

    @Query("SELECT pmg FROM PlanModuleGPA pmg WHERE pmg.plan.planId = :planId AND pmg.plan.user.userId = :userId")
    List<PlanModuleGPA> findByPlanIdAndUserId(@Param("planId") Long planId, @Param("userId") Long userId);

    @Query("SELECT p.module FROM PlanModuleGPA p " +
            "WHERE p.plan.planId.planId = :planId " +
            "AND p.plan.planId.userId = :userId " +
            "AND p.term < :term")
    List<Module> findAllPlanModulesByIdBeforeTerm(@Param("planId") Long planId, @Param("userId") Long userId, @Param("term") int term);

    @Query("SELECT p.module FROM PlanModuleGPA p " +
            "WHERE p.plan.planId.planId = :planId " +
            "AND p.plan.planId.userId = :userId " +
            "AND p.term = :term")
    List<Module> findAllModulesByPlanIdAndTerm(@Param("planId") Long planId, @Param("userId") Long userId, @Param("term") int term);

    @Query("SELECT p.module FROM PlanModuleGPA p " +
            "WHERE p.plan.planId.planId = :planId " +
            "AND p.plan.planId.userId = :userId")
    List<Module> findAllModulesByPlanId(@Param("planId") Long planId, @Param("userId") Long userId);
}
