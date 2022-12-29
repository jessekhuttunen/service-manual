package com.springboot.demo.servicemanual.maintenancetask;

public enum MaintenanceTaskClassification {
    CRITICAL(1),
    MAJOR(2),
    MINOR(3);

    private final Integer priority;

    MaintenanceTaskClassification(final Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return this.priority;
    }
}