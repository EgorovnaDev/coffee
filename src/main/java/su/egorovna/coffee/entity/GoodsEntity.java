package su.egorovna.coffee.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods", schema = "cashtest", catalog = "postgres")
public class GoodsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal cost;

    @OneToMany()
    private List<ChecklinesEntity> checklinesEntities = new ArrayList<>();

    public GoodsEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<ChecklinesEntity> getChecklinesEntities() {
        return checklinesEntities;
    }

    public void setChecklinesEntities(List<ChecklinesEntity> checklinesEntities) {
        this.checklinesEntities = checklinesEntities;
    }

}
