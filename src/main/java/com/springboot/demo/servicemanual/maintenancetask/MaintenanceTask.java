package com.springboot.demo.servicemanual.maintenancetask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.demo.servicemanual.factorydevice.FactoryDevice;

import javax.persistence.*;

@Entity
@Table(name = "MAINTENANCE_TASKS")
public class MaintenanceTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ENTRY_TIME")
    private Long entryTime;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLASSIFICATION")
    private MaintenanceTaskClassification classification;

    @Column(name = "TASK_COMPLETED")
    private Boolean taskCompleted;

    @ManyToOne
    @JoinColumn(name = "DEVICE_ID")
    @JsonIgnore
    private FactoryDevice factoryDevice;

    public Long getId() { return this.id; }

    public void setId(final Long id) { this.id = id; }

    public Long getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(final Long entryTime) {
        this.entryTime = entryTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public MaintenanceTaskClassification getClassification() {
        return this.classification;
    }

    public void setClassification(final MaintenanceTaskClassification classification) {
        this.classification = classification;
    }

    public Boolean getTaskCompleted() { return taskCompleted; }

    public void setTaskCompleted(final Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public FactoryDevice getFactoryDevice() {
        return this.factoryDevice;
    }

    public void setFactoryDevice(final FactoryDevice factoryDevice) {
        this.factoryDevice = factoryDevice;
    }

    public Integer classificationAsInt() {
        return this.classification.getPriority();
    }
}