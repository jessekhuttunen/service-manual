package com.springboot.demo.servicemanual.maintenancetask;

import com.springboot.demo.servicemanual.factorydevice.FactoryDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {

    List<MaintenanceTask> findByFactoryDevice(FactoryDevice factoryDevice);
}
