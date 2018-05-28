package com.smart.incubator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name = "modes")
public class Mode {
    @Column(name = "id")
    private Integer id;

    @Column(name = "mode_name")
    @Size(min = 0, max = 255)
    private String modeName;

    @Column(name = "activation_status")
    @NotNull
    private Boolean activationStatus;

    @Column(name = "up_lim_temperature")
    @NotNull
    private Double upLimTemperature;

    @Column(name = "low_lim_temperature")
    @NotNull
    private Double lowLimTemperature;

    @Column(name = "up_lim_humidity")
    @NotNull
    private Double upLimHumidity;

    @Column(name = "low_lim_humidity")
    @NotNull
    private Double lowLimHumidity;

    @Column(name = "engine_speed")
    @NotNull
    private Integer engineSpeed;

    @Column(name = "work_time")
    @NotNull
    private Integer workTime;

    @Column(name = "create_time")
    private String createTime;


    public Mode() {
    }

    public Mode(String modeName, Double upLimTemperature, Double lowLimTemperature, Double upLimHumidity, Double lowLimHumidity, Integer engineSpeed, Integer workTime) {
        this.modeName = modeName;
        this.activationStatus = false;
        this.upLimTemperature = upLimTemperature;
        this.lowLimTemperature = lowLimTemperature;
        this.upLimHumidity = upLimHumidity;
        this.lowLimHumidity = lowLimHumidity;
        this.engineSpeed = engineSpeed;
        this.workTime = workTime;

        // need for getting current time for adding into new statistic
        Calendar calendar = Calendar.getInstance();
        Timestamp currentTime = new Timestamp(calendar.getTime().getTime());

        this.createTime = currentTime.toString();
    }

    public Mode(String modeName, Boolean activationStatus, Double upLimTemperature, Double lowLimTemperature, Double upLimHumidity, Double lowLimHumidity, Integer engineSpeed, Integer workTime, String createTime) {
        this.modeName = modeName;
        this.activationStatus = activationStatus;
        this.upLimTemperature = upLimTemperature;
        this.lowLimTemperature = lowLimTemperature;
        this.upLimHumidity = upLimHumidity;
        this.lowLimHumidity = lowLimHumidity;
        this.engineSpeed = engineSpeed;
        this.workTime = workTime;
        this.createTime = createTime;
    }

    public Mode(Integer id, String modeName, Boolean activationStatus, Double upLimTemperature, Double lowLimTemperature, Double upLimHumidity, Double lowLimHumidity, Integer engineSpeed, Integer workTime, String createTime) {
        this.id = id;
        this.modeName = modeName;
        this.activationStatus = activationStatus;
        this.upLimTemperature = upLimTemperature;
        this.lowLimTemperature = lowLimTemperature;
        this.upLimHumidity = upLimHumidity;
        this.lowLimHumidity = lowLimHumidity;
        this.engineSpeed = engineSpeed;
        this.workTime = workTime;
        this.createTime = createTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public Boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Double getUpLimTemperature() {
        return upLimTemperature;
    }

    public void setUpLimTemperature(Double upLimTemperature) {
        this.upLimTemperature = upLimTemperature;
    }

    public Double getLowLimTemperature() {
        return lowLimTemperature;
    }

    public void setLowLimTemperature(Double lowLimTemperature) {
        this.lowLimTemperature = lowLimTemperature;
    }

    public Double getUpLimHumidity() {
        return upLimHumidity;
    }

    public void setUpLimHumidity(Double upLimHumidity) {
        this.upLimHumidity = upLimHumidity;
    }

    public Double getLowLimHumidity() {
        return lowLimHumidity;
    }

    public void setLowLimHumidity(Double lowLimHumidity) {
        this.lowLimHumidity = lowLimHumidity;
    }

    public Integer getEngineSpeed() {
        return engineSpeed;
    }

    public void setEngineSpeed(Integer engineSpeed) {
        this.engineSpeed = engineSpeed;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "Mode{" +
                "id=" + id +
                ", modeName='" + modeName + '\'' +
                ", activationStatus=" + activationStatus +
                ", upLimTemperature=" + upLimTemperature +
                ", lowLimTemperature=" + lowLimTemperature +
                ", upLimHumidity=" + upLimHumidity +
                ", lowLimHumidity=" + lowLimHumidity +
                ", engineSpeed=" + engineSpeed +
                ", workTime=" + workTime +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mode mode = (Mode) o;

        if (activationStatus != null ? !activationStatus.equals(mode.activationStatus) : mode.activationStatus != null)
            return false;
        if (engineSpeed != null ? !engineSpeed.equals(mode.engineSpeed) : mode.engineSpeed != null) return false;
        if (id != null ? !id.equals(mode.id) : mode.id != null) return false;
        if (lowLimHumidity != null ? !lowLimHumidity.equals(mode.lowLimHumidity) : mode.lowLimHumidity != null)
            return false;
        if (lowLimTemperature != null ? !lowLimTemperature.equals(mode.lowLimTemperature) : mode.lowLimTemperature != null)
            return false;
        if (upLimHumidity != null ? !upLimHumidity.equals(mode.upLimHumidity) : mode.upLimHumidity != null)
            return false;
        if (upLimTemperature != null ? !upLimTemperature.equals(mode.upLimTemperature) : mode.upLimTemperature != null)
            return false;
        if (workTime != null ? !workTime.equals(mode.workTime) : mode.workTime != null) return false;

        return true;
    }
}
