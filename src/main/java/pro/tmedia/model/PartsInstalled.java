package pro.tmedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 05.08.14
 */


@Entity
@Table(name="parts_installed")
public class PartsInstalled {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer hardware_id;
    private Integer price;
    private Integer count;
    private Integer request_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(Integer hardware_id) {
        this.hardware_id = hardware_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }
}
