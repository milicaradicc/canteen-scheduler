package com.example.demo.dto;

import java.util.List;

public class CanteenCapacityDTO {
    private String canteenId;
    private List<SlotDTO> slots;

    public String getCanteenId() { return canteenId; }
    public void setCanteenId(String canteenId) { this.canteenId = canteenId; }

    public List<SlotDTO> getSlots() { return slots; }
    public void setSlots(List<SlotDTO> slots) { this.slots = slots; }
}