public class Date {
    private int day;
    private int month;
    private int year;

    public Date(String date) {
        String[] temp = date.split("/");
        this.day = Integer.valueOf(temp[0]);
        this.month=Integer.valueOf(temp[1]);
        this.year=Integer.valueOf(temp[2]);
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public String toString() { //To use date object in anywhere.
        return day+"."+month+"."+year; //Obtained date changed to DD.MM.YYYY
    }
}