public class Agency {
    private int agency_id;
    private String name;
    private Address address;
    private Phone phone;

    public Agency(int agency_id,String name, Address address, Phone phone) {
        this.agency_id=agency_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getAgency_id() {
        return this.agency_id;
    }
    public String getName(){
        return this.name;
    }
    public void displayAgency() {
        System.out.println("   AGENCY "+agency_id);
        System.out.println("    Name = "+name);
        System.out.println("    Address = "+address.toString());
        System.out.println("    Phone = "+phone.toString()+"\n");
    }

}