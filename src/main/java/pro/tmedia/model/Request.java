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

    Integer fault_id;
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

    private Integer request_status_id;
    @ManyToOne
    private Task completed_works;

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

    public void setDummyValues() {

        assert model == null;
        model = "Ноутбук";

        assert appearance == null;
        appearance = new Appearance();
        appearance.setName("Не очень");
        appearance.setId(1);

        assert completeness == null;
        completeness = new Completeness();
        completeness.setName("Не очень");
        completeness.setId(1);

        assert address == null;
        address = "123";

        assert customer_name == null;
        customer_name = "123";

        assert source == null;
        source = new Source();
        source.setName("Не очень");
        source.setId(1);

        assert date_of_call == null;
        date_of_call = "1991-01-01";

        assert note == null;
        note = "123";

        assert approximate_cost == null;
        approximate_cost = 0;

        //assert prepayment == null;
        //prepayment = 0;

        assert acceptor == null;
        acceptor = new Employee();
        acceptor.setName("Не очень");
        acceptor.setId(1);

        assert responsible == null;
        responsible = new Employee();
        responsible.setName("Не очень");
        responsible.setId(1);

        assert method_of_payment == null;
        method_of_payment = "123";

        assert completed_works == null;
        completed_works = new Task();
        completed_works.setName("Не очень");
        completed_works.setId(1);

        assert parts_installed == null;
        parts_installed = new Hardware();
        parts_installed.setName("Не очень");
        parts_installed.setId(1);
    }
}
