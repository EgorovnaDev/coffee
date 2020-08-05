package su.egorovna.coffee.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "checklines", schema = "cashtest", catalog = "postgres")
public class ChecklinesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private GoodsEntity goodsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_id")
    private ChecksEntity checksEntity;

    public ChecklinesEntity() {
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public GoodsEntity getGoodsEntity() {
        return goodsEntity;
    }

    public void setGoodsEntity(GoodsEntity goodsEntity) {
        this.goodsEntity = goodsEntity;
    }

    public ChecksEntity getChecksEntity() {
        return checksEntity;
    }

    public void setChecksEntity(ChecksEntity checksEntity) {
        this.checksEntity = checksEntity;
    }
}
