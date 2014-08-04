package pro.tmedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Ivaykin Timofey
 * Date: 04.08.14
 */


@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer engineer_id;
    private Integer price;
    private Integer task_type_id;
    private Integer request_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEngineer_id() {
        return engineer_id;
    }

    public void setEngineer_id(Integer engineer_id) {
        this.engineer_id = engineer_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTask_type_id() {
        return task_type_id;
    }

    public void setTask_type_id(Integer task_type_id) {
        this.task_type_id = task_type_id;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }
}
