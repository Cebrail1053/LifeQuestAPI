package com.gabetech.lifequest.model.enums;

public enum QuestStatus {

    ASSIGNED("Assigned"),
    COMPLETED("Completed");

    private final String status;

    QuestStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
