package pro.tmedia.model;

import javax.persistence.*;


/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue
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
    private String date_of_call;
    private String note;
    private Integer approximate_cost;
    private Integer prepayment;


    private Integer acceptor_id;

    private Integer responsible_id;


    private String date_of_receipt;

    private String date_of_issue;

    private Integer amount;
    private String method_of_payment;

    private Integer request_status_id;

    private Integer completed_works_id;

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

    public String getDate_of_call() {
        return date_of_call;
    }

    public void setDate_of_call(String date_of_call) {
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

    public String getDate_of_receipt() {
        return date_of_receipt;
    }

    public void setDate_of_receipt(String date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
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

    /*public RequestStatus getRequest_status() {
        return request_status;
    }

    public void setRequest_status(RequestStatus request_status) {
        this.request_status = request_status;
    }
             */

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

    public Integer getParts_installed_id() {
        return parts_installed_id;
    }

    public void setParts_installed_id(Integer parts_installed_id) {
        this.parts_installed_id = parts_installed_id;
    }

    public Integer getCompleted_works_id() {
        return completed_works_id;
    }

    public void setCompleted_works_id(Integer completed_works_id) {
        this.completed_works_id = completed_works_id;
    }

    private Integer parts_installed_id;
}
