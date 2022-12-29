package com.springboot.demo.servicemanual.maintenancetask;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MaintenanceTaskControllerTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getMaintenanceTasks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tasks").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMaintenanceTask() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/tasks/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postMaintenanceTask() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(createDummyTask()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMaintenanceTask() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/tasks/1/factorydevice/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private MaintenanceTask createDummyTask() {
        final MaintenanceTask task = new MaintenanceTask();
        task.setId(1L);
        task.setEntryTime(0L);
        task.setDescription("");
        task.setClassification(MaintenanceTaskClassification.CRITICAL);
        task.setTaskCompleted(false);
        return task;
    }
}