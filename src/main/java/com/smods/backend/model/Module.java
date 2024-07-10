package com.smods.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MODULE")
public class Module {

    @Id
    @Column(name = "MODULE_ID")
    private String moduleId;

    @Column(name = "MODULE_NAME")
    private String moduleName;

    @Column(name = "COURSE_UNIT")
    private Float courseUnit;

    // null if not a major module
    @Column(name = "MAJOR")
    private String major;

    // null if not a track module
    @Column(name = "TRACK")
    private String track;

    @Column(name = "GRAD_REQUIREMENT")
    private String gradRequirement;

    @Column(name = "GRAD_SUBREQUIREMENT")
    private String gradSubrequirement;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    @JsonBackReference(value = "module-planModuleGPA")
    private List<PlanModuleGPA> planModuleGPAs;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    @JsonBackReference(value = "module-planModulePreassignedGPA")
    private List<PlanModulePreassignedGPA> planModulePreassignedGPAs;

    @ManyToMany
    @JoinTable(
            name = "PRE_REQUISITE",
            joinColumns = @JoinColumn(name = "MODULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID2")
    )
    private List<Module> preRequisites;

    @ManyToMany(mappedBy = "preRequisites")
    @JsonBackReference(value = "preRequisiteDependents")
    private List<Module> preRequisiteDependents;

    @ManyToMany
    @JoinTable(
            name = "CO_REQUISITE",
            joinColumns = @JoinColumn(name = "MODULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID2")
    )
    private List<Module> coRequisites;

    @ManyToMany(mappedBy = "coRequisites")
    @JsonBackReference(value = "coRequisiteDependents")
    private List<Module> coRequisiteDependents;

    @ManyToMany
    @JoinTable(
            name = "MUTUALLY_EXCLUSIVE",
            joinColumns = @JoinColumn(name = "MODULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID2")
    )
    private List<Module> mutuallyExclusives;

    @ManyToMany(mappedBy = "mutuallyExclusives")
    @JsonBackReference(value = "mutuallyExclusiveWith")
    private List<Module> mutuallyExclusiveWith;

    // Default constructor
    public Module() {}

    public Module(String moduleId, String moduleName, Float courseUnit, String major, String track, String gradRequirement, String gradSubrequirement) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.courseUnit = courseUnit;
        this.major = major;
        this.track = track;
        this.gradRequirement = gradRequirement;
        this.gradSubrequirement = gradSubrequirement;
    }

    // Getters and Setters
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Float getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(Float courseUnit) {
        this.courseUnit = courseUnit;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getGradRequirement() {
        return gradRequirement;
    }

    public void setGradRequirement(String gradRequirement) {
        this.gradRequirement = gradRequirement;
    }

    public String getGradSubrequirement() {
        return gradSubrequirement;
    }

    public void setGradSubrequirement(String gradSubrequirement) {
        this.gradSubrequirement = gradSubrequirement;
    }

    public List<PlanModuleGPA> getPlanModuleGPAs() {
        return planModuleGPAs;
    }

    public void setPlanModuleGPAs(List<PlanModuleGPA> planModuleGPAs) {
        this.planModuleGPAs = planModuleGPAs;
    }

    public List<PlanModulePreassignedGPA> getPlanModulePreassignedGPAs() {
        return planModulePreassignedGPAs;
    }

    public void setPlanModulePreassignedGPAs(List<PlanModulePreassignedGPA> planModulePreassignedGPAs) {
        this.planModulePreassignedGPAs = planModulePreassignedGPAs;
    }

    public List<Module> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(List<Module> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public List<Module> getPreRequisiteDependents() {
        return preRequisiteDependents;
    }

    public void setPreRequisiteDependents(List<Module> preRequisiteDependents) {
        this.preRequisiteDependents = preRequisiteDependents;
    }

    public List<Module> getCoRequisites() {
        return coRequisites;
    }

    public void setCoRequisites(List<Module> coRequisites) {
        this.coRequisites = coRequisites;
    }

    public List<Module> getCoRequisiteDependents() {
        return coRequisiteDependents;
    }

    public void setCoRequisiteDependents(List<Module> coRequisiteDependents) {
        this.coRequisiteDependents = coRequisiteDependents;
    }

    public List<Module> getMutuallyExclusives() {
        return mutuallyExclusives;
    }

    public void setMutuallyExclusives(List<Module> mutuallyExclusives) {
        this.mutuallyExclusives = mutuallyExclusives;
    }

    public List<Module> getMutuallyExclusiveWith() {
        return mutuallyExclusiveWith;
    }

    public void setMutuallyExclusiveWith(List<Module> mutuallyExclusiveWith) {
        this.mutuallyExclusiveWith = mutuallyExclusiveWith;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Objects.equals(moduleId, module.moduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId);
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId='" + moduleId + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", courseUnit=" + courseUnit +
                '}';
    }
}
