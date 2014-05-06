package pro.tmedia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User: Ivaykin Timofey
 * Date: 06/05/14
 */
@Entity
@Table(name="warehouse_item")
public class WarehouseItem {
    @Id
    private String invoice_number;
    Integer hardware_id;
    Integer item_count;
    Integer repair_price;
    Integer provider_id;
    String warranty;

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public Integer getItem_count() {
        return item_count;
    }

    public void setItem_count(Integer item_count) {
        this.item_count = item_count;
    }

    public Integer getRepair_price() {
        return repair_price;
    }

    public void setRepair_price(Integer repair_price) {
        this.repair_price = repair_price;
    }

    public Integer getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(Integer provider_id) {
        this.provider_id = provider_id;
    }

    public Integer getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(Integer hardware_id) {
        this.hardware_id = hardware_id;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public void setDummyValues() {
        hardware_id = 1;
        warranty = "нет";
    }
}
