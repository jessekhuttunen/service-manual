package com.springboot.demo.servicemanual.factorydevice;

import com.springboot.demo.servicemanual.ServiceManualConstants;
import com.springboot.demo.servicemanual.maintenancetask.MaintenanceTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FactoryDeviceController {

    private final FactoryDeviceRepository repository;

    FactoryDeviceController(final FactoryDeviceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/factorydevices")
    List<FactoryDevice> all() {
        return repository.findAll();
    }

    @GetMapping("/factorydevices/{id}")
    FactoryDevice one(@PathVariable final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FactoryDeviceNotFoundException(id));
    }

    @GetMapping("factorydevices/{id}/tasks")
    public List<MaintenanceTask> getMaintenanceTasksByFactoryId(@PathVariable final Long id) {
        return one(id)
                .getMaintenanceTasks()
                .stream()
                .sorted(ServiceManualConstants.TASK_COMPARATOR)
                .collect(Collectors.toList());
    }
}