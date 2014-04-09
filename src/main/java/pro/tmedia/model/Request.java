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
    @ManyToOne
    @JoinColumn(name="hardware_id")
    private Hardware hardware;
    @ManyToOne
    private Manufacturer manufacturer;
    private String model;
    private String serial_number;
    @ManyToOne
    private Fault fault;
    @ManyToOne
    private Appearance appearance;
    @ManyToOne
    private Completeness completeness;
    private String phone;
    private String address;
    private String customer_name;
    @ManyToOne
    private Source source;
    private String date_of_call;
    private String note;
    private Integer approximate_cost;
    private Integer prepayment;

    @ManyToOne
    Employee acceptor;
    @ManyToOne
    Employee responsible;
    private String date_of_receipt;
    private String date_of_issue;
    Integer amount;
    private String method_of_payment;
    @ManyToOne
    private RequestStatus request_status;
    @ManyToOne
    private Task completed_works;

    public Integer getReq_num_id() {
        return req_num_id;
    }

    public void setReq_num_id(Integer req_num_id) {
        this.req_num_id = req_num_id;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

    public Fault getFault() {
        return fault;
    }

    public void setFault(Fault fault) {
        this.fault = fault;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Completeness getCompleteness() {
        return completeness;
    }

    public void setCompleteness(Completeness completeness) {
        this.completeness = completeness;
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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
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

    public Employee getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(Employee acceptor) {
        this.acceptor = acceptor;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
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

    public RequestStatus getRequest_status() {
        return request_status;
    }

    public void setRequest_status(RequestStatus request_status) {
        this.request_status = request_status;
    }

    public Task getCompleted_works() {
        return completed_works;
    }

    public void setCompleted_works(Task completed_works) {
        this.completed_works = completed_works;
    }

    public Hardware getParts_installed() {
        return parts_installed;
    }

    public void setParts_installed(Hardware parts_installed) {
        this.parts_installed = parts_installed;
    }

    @ManyToOne
    Hardware parts_installed;
}
