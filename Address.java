public class Address {
    private String openAd;
    private String town;
    private String city;

    public Address(String openAd, String town, String city) {
        this.openAd = openAd;
        this.town = town;
        this.city = city;
    }
    public String getTown(){
        return town;
    }
    public String getCity(){
        return city;
    }
    public String toString() { // To use address object in any displaying methods or anywhere.
        return openAd+" "+town+" "+city;
    }

}