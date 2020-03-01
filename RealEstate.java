public class RealEstate
{
    private int real_estate_id;
    private String type;
    private String status;
    private Address address;
    private double surface_area;
    private double price;
    private int number_of_rooms;
    private boolean flag = true;
    public RealEstate(int real_estate_id,String type, String status, Address address, double surface_area, double price,
                      int number_of_rooms) {
        this.real_estate_id=real_estate_id;
        this.type = type;
        this.status = status;
        this.address = address;
        this.surface_area = surface_area;
        this.price = price;
        this.number_of_rooms = number_of_rooms;
    }

    public int getReal_estate_id() {
        return real_estate_id;
    }
    public boolean getFlag(){return flag;}
    public String getType(){
        return type;
    }
    public String getStatus(){
        return status;
    }
    public String getTown(){
        return address.getTown();
    }
    public String getCity(){
        return address.getCity();
    }
    public double getSurface_area(){
        return surface_area;
    }
    public double getPrice(){
        return price;
    }
    public int getNumber_of_rooms(){
        return number_of_rooms;
    }


    public void delete() {
        flag = false;
    }
    public void displayRealEstate() {
        System.out.println("   REAL ESTATE "+real_estate_id);
        System.out.println("    Type = "+type);
        System.out.println("    Status = "+status);
        System.out.println("    Address = "+address.toString());
        System.out.println("    Surface Area = "+surface_area+" m^2");
        System.out.println("    Price = "+price+" TL");
        System.out.println("    Room Number = "+number_of_rooms+"\n");
    }}