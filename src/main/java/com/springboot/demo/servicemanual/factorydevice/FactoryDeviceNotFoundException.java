package com.springboot.demo.servicemanual.factorydevice;

import java.io.Serial;

public class FactoryDeviceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FactoryDeviceNotFoundException(Long id) {
        super("Could not find factory device with ID " + id);
    }
}
