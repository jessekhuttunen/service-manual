package com.springboot.demo.servicemanual.maintenancetask;

import java.io.Serial;

class MaintenanceTaskNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    MaintenanceTaskNotFoundException(Long id) {
        super("Could not find maintenance task with ID " + id);
    }
}
