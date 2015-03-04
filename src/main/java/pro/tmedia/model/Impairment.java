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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_impairment() {
        return date_impairment;
    }

    public void setDate_impairment(Date date_impairment) {
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
