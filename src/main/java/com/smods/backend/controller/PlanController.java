package com.smods.backend.controller;

import com.smods.backend.model.Plan;
import com.smods.backend.model.PlanModuleGPA;
import com.smods.backend.security.CustomUserDetails;
import com.smods.backend.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<Plan> plans = planService.getAllPlansByUser(userId);
        return ResponseEntity.ok(plans);
    }

    @PostMapping
    public ResponseEntity<Plan> createPlan(Authentication authentication, @RequestBody Plan plan) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        Plan createdPlan = planService.createPlan(userId, plan);
        return ResponseEntity.ok(createdPlan);
    }

    @PutMapping("/add-module")
    public ResponseEntity<PlanModuleGPA> addModule(
            @RequestParam Long planId,
            @RequestParam Long userId,
            @RequestParam String moduleId,
            @RequestParam int term) {
        PlanModuleGPA updatedModule = planService.addModule(planId, userId, moduleId, term);
        return ResponseEntity.ok(updatedModule);
    }
}
