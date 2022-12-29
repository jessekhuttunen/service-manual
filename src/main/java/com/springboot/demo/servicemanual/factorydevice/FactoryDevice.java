package com.springboot.demo.servicemanual.factorydevice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.demo.servicemanual.maintenancetask.MaintenanceTask;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FACTORY_DEVICES")
public class FactoryDevice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MANUFACTURING_YEAR")
    private int year;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "factoryDevice")
    @JsonIgnore
    private List<MaintenanceTask> maintenanceTasks;

    protected FactoryDevice() {}

    /**
     * Constructor of FactoryDevice class
     *
     * @param name Name of the device
     * @param year Year of the device
     * @param type Type of the device
     */
    public FactoryDevice(final String name, final int year, final String type) {
        this.name = name;
        this.year = year;
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public String getType() {
        return this.type;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public List<MaintenanceTask> getMaintenanceTasks() {
        return this.maintenanceTasks;
    }

    public void setMaintenanceTasks(final List<MaintenanceTask> maintenanceTasks) {
        this.maintenanceTasks = maintenanceTasks;
    }
}