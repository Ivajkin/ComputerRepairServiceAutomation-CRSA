package pro.tmedia.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * User: Ivaykina Galina
 * Date: 04.03.15
 */


@Entity
@Table(name="impairment")
public class Impairment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="impairment_id_seq")
    @SequenceGenerator(name="impairment_id_seq", sequenceName="impairment_id_seq", allocationSize=1)
    private Integer id;
    private java.sql.Date date_impairment;
    private Integer impairment_type_id;
    private Integer warehouse_item_id;
    private Integer count_hardware_impairment;

    public Integer getCount_hardware_impairment() {
        return count_hardware_impairment;
    }

    public void setCount_hardware_impairment(Integer count_hardware_impairment) {
        if(count_hardware_impairment <= 0) {
            throw new IllegalArgumentException("Минимальное количество, которое можно списать должно быть 1");
        }
        this.count_hardware_impairment = count_hardware_impairment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_impairment() {
        return date_impairment;
    }

    public void setDate_impairment(java.sql.Date date_impairment) {
        this.date_impairment = date_impairment;
    }

    public Integer getImpairment_type_id() {
        return impairment_type_id;
    }

    public void setImpairment_type_id(Integer impairment_type_id) {
        this.impairment_type_id = impairment_type_id;
    }

    public Integer getWarehouse_item_id() {
        return warehouse_item_id;
    }

    public void setWarehouse_item_id(Integer warehouse_item_id) {
        this.warehouse_item_id = warehouse_item_id;
    }
}
