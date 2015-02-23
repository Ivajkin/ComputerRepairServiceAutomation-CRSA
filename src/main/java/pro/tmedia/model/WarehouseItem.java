package pro.tmedia.model;

import javax.persistence.*;
import java.sql.Date;


/**
 * User: Ivaykin Timofey
 * Date: 06/05/14
 */
@Entity
@Table(name="warehouse_item")
public class WarehouseItem {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="warehouse_item_id_seq")
    @SequenceGenerator(name="warehouse_item_id_seq", sequenceName="warehouse_item_id_seq", allocationSize=1)
    Integer id;

    Integer category_id;
    Integer hardware_id;
    Integer manufacturer_id;
    String model;
    String serial_number;
    private String invoice_number;
    Integer provider_id;
    String note;



    private java.sql.Date posting_date;
    Integer item_count;
    Integer repair_price_percent;
    String warranty;
    Integer purchase_price;
    Integer zero_price_percent;
    Integer retail_price_percent;
    Integer its_price_percent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepair_price_percent() {
        return repair_price_percent;
    }

    public void setRepair_price_percent(Integer repair_price_percent) {
        this.repair_price_percent = repair_price_percent;
    }



    public Integer getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(Integer purchase_price) {
        this.purchase_price = purchase_price;
    }



    public Integer getIts_price_percent() {
        return its_price_percent;
    }

    public void setIts_price_percent(Integer its_price_percent) {
        this.its_price_percent = its_price_percent;
    }

    public Integer getRetail_price_percent() {
        return retail_price_percent;
    }

    public void setRetail_price_percent(Integer retail_price_percent) {
        this.retail_price_percent = retail_price_percent;
    }

    public Integer getZero_price_percent() {
        return zero_price_percent;
    }

    public void setZero_price_percent(Integer zero_price_percent) {
        this.zero_price_percent = zero_price_percent;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(Integer manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getItem_count() {
        return item_count;
    }

    public void setItem_count(Integer item_count) {
        this.item_count = item_count;
    }

    public Date getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(Date posting_date) {
        this.posting_date = posting_date;
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

}
