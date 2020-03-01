public class Customer
{
    private int customer_id;
    private String name;
    private Date birthdate;
    private Address address;
    private Phone phone;
    private String gender;
    private boolean flag = true;
    public Customer(int customer_id,String name, Date birthdate, Address address, Phone phone, String gender) {
        this.customer_id=customer_id;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public int getCustomer_id(){
        return customer_id;
    }
    public String getName(){
        return name;
    }
    public boolean getFlag(){return flag;}
    public void delete() {
        flag = false;
    }
    public void displayCustomer() {
        System.out.println("   CUSTOMER "+customer_id);
        System.out.println("    Name = "+name);
        System.out.println("    Gender = "+gender);
        System.out.println("    Birthdate = "+birthdate.toString());
        System.out.println("    Address = "+address.toString());
        System.out.println("    Phone = "+phone.toString()+"\n");
    }
}