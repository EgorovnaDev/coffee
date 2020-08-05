package su.egorovna.coffee.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "checks", schema = "cashtest", catalog = "postgres")
public class ChecksEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private LocalTime time;
    private Double sum;

    @OneToMany()
    private List<ChecklinesEntity> checklinesEntities = new ArrayList<>();

    public ChecksEntity() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<ChecklinesEntity> getChecklinesEntities() {
        return checklinesEntities;
    }

    public void setChecklinesEntities(List<ChecklinesEntity> checklinesEntities) {
        this.checklinesEntities = checklinesEntities;
    }
}
