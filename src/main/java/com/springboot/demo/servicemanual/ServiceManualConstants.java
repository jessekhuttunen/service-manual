package com.springboot.demo.servicemanual;

import com.springboot.demo.servicemanual.maintenancetask.MaintenanceTask;

import java.util.Comparator;

public class ServiceManualConstants {

    public static final Comparator<MaintenanceTask> TASK_COMPARATOR = Comparator
            .comparingInt(MaintenanceTask::classificationAsInt)
            .thenComparing(MaintenanceTask::getEntryTime, Comparator.reverseOrder());
}
