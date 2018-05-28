package com.smart.incubator.domain;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statistics")
public class Statistic {
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "temperature_sensor_1")
    @JsonProperty("temperature_sensor_1")
    private Double temperatureSensor_1;

    @Column(name = "temperature_sensor_2")
    @JsonProperty("temperature_sensor_2")
    private Double temperatureSensor_2;

    @Column(name = "temperature_sensor_3")
    @JsonProperty("temperature_sensor_3")
    private Double temperatureSensor_3;

    @Column(name = "humidity_sensor_1")
    @JsonProperty("humidity_sensor_1")
    private Double humiditySensor_1;

    @Column(name = "create_time")
    @JsonProperty("create_time")
    private String createTime;


    public Statistic() {
    }

    public Statistic(Double temperatureSensor_1, Double temperatureSensor_2, Double temperatureSensor_3, Double humiditySensor_1, String createTime) {
        this.temperatureSensor_1 = temperatureSensor_1;
        this.temperatureSensor_2 = temperatureSensor_2;
        this.temperatureSensor_3 = temperatureSensor_3;
        this.humiditySensor_1 = humiditySensor_1;
        this.createTime = createTime;
    }

    public Statistic(Integer id, Double temperatureSensor_1, Double temperatureSensor_2, Double temperatureSensor_3, Double humiditySensor_1, String createTime) {
        this.id = id;
        this.temperatureSensor_1 = temperatureSensor_1;
        this.temperatureSensor_2 = temperatureSensor_2;
        this.temperatureSensor_3 = temperatureSensor_3;
        this.humiditySensor_1 = humiditySensor_1;
        this.createTime = createTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperatureSensor_1() {
        return temperatureSensor_1;
    }

    public void setTemperatureSensor_1(Double temperatureSensor_1) {
        this.temperatureSensor_1 = temperatureSensor_1;
    }

    public Double getTemperatureSensor_2() {
        return temperatureSensor_2;
    }

    public void setTemperatureSensor_2(Double temperatureSensor_2) {
        this.temperatureSensor_2 = temperatureSensor_2;
    }

    public Double getTemperatureSensor_3() {
        return temperatureSensor_3;
    }

    public void setTemperatureSensor_3(Double temperatureSensor_3) {
        this.temperatureSensor_3 = temperatureSensor_3;
    }

    public Double getHumiditySensor_1() {
        return humiditySensor_1;
    }

    public void setHumiditySensor_1(Double humiditySensor_1) {
        this.humiditySensor_1 = humiditySensor_1;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", temperatureSensor_1=" + temperatureSensor_1 +
                ", temperatureSensor_2=" + temperatureSensor_2 +
                ", temperatureSensor_3=" + temperatureSensor_3 +
                ", humiditySensor_1=" + humiditySensor_1 +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;

        if (humiditySensor_1 != null ? !humiditySensor_1.equals(statistic.humiditySensor_1) : statistic.humiditySensor_1 != null)
            return false;
        if (id != null ? !id.equals(statistic.id) : statistic.id != null) return false;
        if (temperatureSensor_1 != null ? !temperatureSensor_1.equals(statistic.temperatureSensor_1) : statistic.temperatureSensor_1 != null)
            return false;
        if (temperatureSensor_2 != null ? !temperatureSensor_2.equals(statistic.temperatureSensor_2) : statistic.temperatureSensor_2 != null)
            return false;
        if (temperatureSensor_3 != null ? !temperatureSensor_3.equals(statistic.temperatureSensor_3) : statistic.temperatureSensor_3 != null)
            return false;

        return true;
    }
}
