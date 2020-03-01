public class Phone {
    private String country_code;
    private String city_code;
    private String number;

    public Phone(String phone) {
        this.country_code = phone.substring(0,4);
        this.city_code = phone.substring(4,7);
        this.number = phone.substring(7);
    }
    public String toString() { //To use phone object in anywhere.
        return country_code+" "+city_code+" "+number;
    }
}