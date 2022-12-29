package com.springboot.demo.servicemanual.maintenancetask;

import com.springboot.demo.servicemanual.ServiceManualConstants;
import com.springboot.demo.servicemanual.factorydevice.FactoryDevice;
import com.springboot.demo.servicemanual.factorydevice.FactoryDeviceNotFoundException;
import com.springboot.demo.servicemanual.factorydevice.FactoryDeviceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class MaintenanceTaskController {

    private final MaintenanceTaskRepository taskRepo;

    private final FactoryDeviceRepository factoryDeviceRepo;

    MaintenanceTaskController(final MaintenanceTaskRepository taskRepo,
                              final FactoryDeviceRepository factoryDeviceRepo) {
        this.taskRepo = Objects.requireNonNull(taskRepo);
        this.factoryDeviceRepo = Objects.requireNonNull(factoryDeviceRepo);
    }

    @GetMapping("/tasks")
    public List<MaintenanceTask> getAllMaintenanceTasks() {
        return this.taskRepo.findAll()
                .stream()
                .sorted(ServiceManualConstants.TASK_COMPARATOR)
                .collect(Collectors.toList());
    }

    @PostMapping("/tasks")
    public MaintenanceTask addMaintenanceTask(@RequestBody final MaintenanceTask task) {
        return this.taskRepo.save(task);
    }

    @PutMapping("/tasks/{id}")
    public MaintenanceTask updateMaintenanceTask(@PathVariable final Long id, @RequestBody final MaintenanceTask task) {
        final MaintenanceTask taskToBeUpdated = getTaskIfPresent(id);
        if (task.getFactoryDevice() != null) {
            taskToBeUpdated.setFactoryDevice(task.getFactoryDevice());
        }
        if (task.getEntryTime() != null) {
            taskToBeUpdated.setEntryTime(task.getEntryTime());
        }
        if (task.getDescription() != null) {
            taskToBeUpdated.setDescription(task.getDescription());
        }
        if (task.getClassification() != null) {
            taskToBeUpdated.setClassification(task.getClassification());
        }
        if (task.getTaskCompleted() != null) {
            taskToBeUpdated.setTaskCompleted(task.getTaskCompleted());
        }
        return this.taskRepo.save(taskToBeUpdated);
    }

    @PutMapping("/tasks/{taskId}/factorydevice/{deviceId}")
    public MaintenanceTask assignTaskToFactoryDevice(@PathVariable final Long taskId,
                                                     @PathVariable final Long deviceId) {
        final MaintenanceTask taskToBeUpdated = getTaskIfPresent(taskId);
        final FactoryDevice device = getDeviceIfPresent(deviceId);

        taskToBeUpdated.setFactoryDevice(device);
        return this.taskRepo.save(taskToBeUpdated);
    }

    @DeleteMapping("/tasks/{id}")
    public MaintenanceTask deleteMaintenanceTask(@PathVariable final Long id) {
        final MaintenanceTask taskToBeDeleted = getTaskIfPresent(id);
        this.taskRepo.deleteById(id);
        return taskToBeDeleted;
    }

    private MaintenanceTask getTaskIfPresent(final Long id) {
        return this.taskRepo.findById(id).orElseThrow(() -> new MaintenanceTaskNotFoundException(id));
    }

    private FactoryDevice getDeviceIfPresent(final Long id) {
        return this.factoryDeviceRepo.findById(id).orElseThrow(() -> new FactoryDeviceNotFoundException(id));
    }
}