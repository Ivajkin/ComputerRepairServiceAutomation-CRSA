package pro.tmedia.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Entity
@Table(name="request")
public class Request implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="request_req_num_id_seq")
    @SequenceGenerator(name="request_req_num_id_seq", sequenceName="request_req_num_id_seq", allocationSize=1)
    private Integer req_num_id;

    private Integer hardware_id;

    public Integer getHardware_id() {
        return hardware_id;
    }

    public void setHardware_id(Integer hardware_id) {
        this.hardware_id = hardware_id;
    }

    private Integer manufacturer_id;

    public Integer getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(Integer manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    private String model;
    private String serial_number;

    public String getRequest_number() {
        return request_number;
    }

    public void setRequest_number(String request_number) {
        this.request_number = request_number;
    }

    private String request_number;

    private Integer fault_id;

    private Integer appearance_id;

    private Integer completeness_id;
    private String phone;
    private String address;
    private String customer_name;

    private Integer source_id;
    private java.sql.Date date_of_call;
    private String note;
    private Integer approximate_cost;
    private Integer prepayment;

    private Boolean delivery;
    private Integer slot_id;

    public Boolean getDelivery() {
        return delivery;
    }

    public Integer getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(Integer slot_id) {
        this.slot_id = slot_id;
    }

    public Boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    private Integer acceptor_id;

    private Integer responsible_id;


    private java.sql.Date date_of_receipt;

    private java.sql.Date date_of_issue;

    private Integer amount;
    private String method_of_payment;

    private Integer request_status_id;

    public Integer getReq_num_id() {
        return req_num_id;
    }

    public void setReq_num_id(Integer req_num_id) {
        this.req_num_id = req_num_id;
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

    public Integer getFault_id() {
        return fault_id;
    }

    public void setFault_id(Integer fault_id) {
        this.fault_id = fault_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public java.sql.Date getDate_of_call() {
        return date_of_call;
    }

    public void setDate_of_call(java.sql.Date date_of_call) {
        this.date_of_call = date_of_call;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getApproximate_cost() {
        return approximate_cost;
    }

    public void setApproximate_cost(Integer approximate_cost) {
        this.approximate_cost = approximate_cost;
    }

    public Integer getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Integer prepayment) {
        this.prepayment = prepayment;
    }

    public java.sql.Date getDate_of_receipt() {
        return date_of_receipt;
    }

    public void setDate_of_receipt(java.sql.Date date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }

    public java.sql.Date getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(java.sql.Date date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMethod_of_payment() {
        return method_of_payment;
    }

    public void setMethod_of_payment(String method_of_payment) {
        this.method_of_payment = method_of_payment;
    }

    public Integer getRequest_status_id() {
        return request_status_id;
    }

    public void setRequest_status_id(Integer request_status_id) {
        this.request_status_id = request_status_id;
    }

    public Integer getAppearance_id() {
        return appearance_id;
    }

    public void setAppearance_id(Integer appearance_id) {
        this.appearance_id = appearance_id;
    }

    public Integer getCompleteness_id() {
        return completeness_id;
    }

    public void setCompleteness_id(Integer completeness_id) {
        this.completeness_id = completeness_id;
    }

    public Integer getSource_id() {
        return source_id;
    }

    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public Integer getAcceptor_id() {
        return acceptor_id;
    }

    public void setAcceptor_id(Integer acceptor_id) {
        this.acceptor_id = acceptor_id;
    }

    public Integer getResponsible_id() {
        return responsible_id;
    }

    public void setResponsible_id(Integer responsible_id) {
        this.responsible_id = responsible_id;
    }

}
