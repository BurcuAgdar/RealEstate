import java.io.File; //For file reading.
import java.util.Scanner; //For file reading.

public class Management {
    public static class FileCount{ //Class for get the dynamic count numbers.
        private int numberOfAgency=0;
        private int numberOfAgent=0;
        private int numberOfRealEstate=0;
        private int numberOfCustomer=0;
        private int numberOfContract=0;

        public FileCount() { //Reading text and get the add commands counts.
            try{
                File text = new File("C:/Users/lenovo/Desktop/input2.txt");
                Scanner scan = new Scanner(text,"UTF-8");
                while(scan.hasNext()) {
                    String temp = scan.nextLine();
                    if(temp.substring(0, 1).equals("?")) temp = temp.substring(1); //In UTF-8 charset, if there is bom char at the begin, it becomes "?". Checking for bom char.
                    String[] splitted = temp.split(";");
                    if(splitted[0].equals("addAgency")) this.numberOfAgency++;
                    else if(splitted[0].equals("addAgent")) this.numberOfAgent++;
                    else if(splitted[0].equals("addRealEstate")) this.numberOfRealEstate++;
                    else if(splitted[0].equals("addCustomer")) this.numberOfCustomer++;
                    else if(splitted[0].equals("addContract")) this.numberOfContract++;
                }
            }catch(java.io.FileNotFoundException e){
                System.out.println("File not found!");
            }
        }

        public int getNumberOfAgency() {
            return this.numberOfAgency;
        }
        public int getNumberOfAgent() {
            return this.numberOfAgent;
        }
        public int getNumberOfRealEstate(){
            return this.numberOfRealEstate;
        }
        public int getNumberOfCustomer(){
            return this.numberOfCustomer;
        }
        public int getNumberOfContract(){
            return this.numberOfContract;
        }
    }
    static FileCount fc = new FileCount();

    private static int countAgency=1,countAgent=1,countContract=1,countCustomer=1,countRealEstate=1; //Arrays starting in 1. index because this way is nice for adding or getting id.
    static Agency agencies[] = new Agency[fc.getNumberOfAgency()+5];
    static Agent agents[] = new Agent[fc.getNumberOfAgent()+10];
    static Contract contracts[] = new Contract[fc.getNumberOfContract()+15];
    static Customer customers[] = new Customer[fc.getNumberOfCustomer()+10];
    static RealEstate realEstates[] = new RealEstate[fc.getNumberOfRealEstate()+20];
    // Arrays have extra spaces for manual adding operations.

    public void fileOperations() { //In this part file is reading line by line. Each line sending to router function.
        try{
            File text = new File("C:/Users/lenovo/Desktop/input2.txt");
            Scanner scan = new Scanner(text, "UTF-8");
            while(scan.hasNext()) {
                String temp = scan.nextLine();
                if(temp.substring(0, 1).equals("?")) temp = temp.substring(1); //For bom char.
                router(temp);
            }
        }catch(java.io.FileNotFoundException e) {
            System.out.println("ERROR ! File not found!");
        }
    }

    public void router(String line) { //In this part, Line splitted to words of its. 0. index of splitted words is command.According to command, this function sending to its method.
        String[] splitted = line.split(";",-1); //If there is no word in between two ";",then it will be ""(empty string).
        if(splitted[0].contains("addAgency")){
            addAgency(splitted);
        }
        else if(splitted[0].contains("addAgent")){
            addAgent(splitted);
        }
        else if(splitted[0].contains("addRealEstate")){
            addRealEstate(splitted);
        }
        else if(splitted[0].contains("addCustomer")){
            addCustomer(splitted);
        }
        else if(splitted[0].contains("addContract")){
            addContract(splitted);
        }
        else if(splitted[0].contains("deleteAgent")){
            deleteAgent(splitted);
        }
        else if(splitted[0].contains("deleteRealEstate")){
            deleteRealEstate(splitted);
        }
        else if(splitted[0].contains("deleteCustomer")){
            deleteCustomer(splitted);
        }
        else if(splitted[0].contains("displayAgencies")){
            displayAgencies();
        }
        else if(splitted[0].contains("displayAgents")){
            displayAgents();
        }
        else if(splitted[0].contains("displayRealEstates")){
            displayRealEstates();
        }
        else if(splitted[0].contains("displayCustomers")){
            displayCustomers();
        }
        else if(splitted[0].contains("displayContracts")){
            displayContracts();
        }
        else if(splitted[0].contains("search")){
            search(splitted);
        }
        else if(splitted[0].contains("calculateSalaries")){
            calculateSalaries(splitted);
        }
        else if(splitted[0].contains("calculateTotalIncome")){
            calculateTotalIncome(splitted);
        }
        else if(splitted[0].contains("mostProfitableAgency")){
            mostProfitableAgency(splitted);
        }
        else if(splitted[0].contains("agentOfTheMonth")){
            agentOfTheMonth(splitted);
        }

    }

    //For adding operations, count will increase even if the operation fails.
    //This means,that index in array is reserved for obtained input line.

    public void addAgency(String[] splitted) {
        System.out.println("\n-----> ADD AGENCY");
        Address tempAddress = new Address(splitted[2],splitted[3],splitted[4]); //Creating an address object with using open address, town and city.
        Phone tempPhone = new Phone(splitted[5]); //Creating a phone object.
        agencies[countAgency] = new Agency(countAgency,splitted[1],tempAddress,tempPhone); //After create all necessary objects, agent will be created.
        countAgency++;
        System.out.println("    Agency successfully added.");
    }

    public void addAgent(String[] splitted) {
        System.out.println("\n-----> ADD AGENT");
        try{ //Try-catch block for checking agency id.
            if(checkDate(splitted[3])) { //Checking if date is valid or invalid.
                splitted[3] = splitted[3].replace(" ", ""); //If date line has space, that will remove it.
                Address tempAddress = new Address(splitted[4], splitted[5], splitted[6]); //Creating an addres object.
                Date tempBirthdate = new Date(splitted[3]); //Creating a date object for birthdate.
                Phone tempPhone = new Phone(splitted[7]); //Creating a phone object.
                int agency_id = agencies[Integer.valueOf(splitted[1])].getAgency_id(); //Using this way to get exceptions if exists.
                agents[countAgent] = new Agent(countAgent, agency_id, splitted[2], tempBirthdate, tempAddress, tempPhone, splitted[8]);
                countAgent++;
                System.out.println("    Agent successfully added.");
            }
            else{
                System.out.println("    ERROR ! Invalid date!"); //If date is invalid.
                countAgent++;
            }
        }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){ //If there is no agency for that id.
            System.out.println("    ERROR ! Invalid agency id!");
            countAgent++;
        }
    }

    public void addRealEstate(String[] splitted) {
        System.out.println("\n-----> ADD REAL ESTATE");
        String status = splitted[2];
        double price = Double.valueOf(splitted[7]);
        if((status.equals("For Rent") && (price<0 || price >5000)) || (status.equals("For Sale") && price<15000)) { //Checking for sensible price.
            System.out.println("    ERROR ! Price cannot be acceptable for "+status+"!");
            countRealEstate++;
        }
        else{
            Address tempAddress = new Address(splitted[3], splitted[4], splitted[5]); //open ad , town , city.
            realEstates[countRealEstate] = new RealEstate(countRealEstate, splitted[1],status, tempAddress, Double.valueOf(splitted[6]),price, Integer.valueOf(splitted[8]));
            countRealEstate++;
            System.out.println("    Real estate successfully added.");
        }
    }

    public void addCustomer(String[] splitted) {
        System.out.println("\n-----> ADD CUSTOMER");
        if(checkDate(splitted[2])) { //Checking date is valid or invalid.
            splitted[2] = splitted[2].replace(" ", "");
            Address tempAddress = new Address(splitted[3], splitted[4], splitted[5]);
            Date tempBirthdate = new Date(splitted[2]);
            Phone tempPhone = new Phone(splitted[6]);
            customers[countCustomer] = new Customer(countCustomer, splitted[1], tempBirthdate, tempAddress, tempPhone, splitted[7]);
            countCustomer++;
            System.out.println("    Customer successfully added.");
        }
        else{
            System.out.println("    ERROR ! Invalid date!");
            countCustomer++;
        }
    }

    public void addContract(String[] splitted) {

        // boolean gate is used go step by step among try-catch blocks.It can be thought as check situation

        System.out.println("\n-----> ADD CONTRACT");
        boolean gate = true;
        int real_estate_id=0,customer_id=0,agent_id=0;
        Date temp_contract_date = null;
        try{ // This try-catch's aim is checking real estate id is valid or invalid.
            if(checkDate(splitted[4])) { //Checking date is valid or invalid.
                splitted[4] = splitted[4].replace(" ", "");
                temp_contract_date = new Date(splitted[4]);
                real_estate_id = realEstates[Integer.valueOf(splitted[1])].getReal_estate_id(); //Using this way to get exceptions if exists.
            }
            else{
                System.out.println("    ERROR ! Invalid date!");
                countContract++;
                gate = false; // If got an exception, gate will be locked for further steps. Object wont be added.
            }
        }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("    ERROR ! Invalid real estate id!");
            gate = false;  // If got an exception, gate will be locked for further steps. Object wont be added.
            countContract++;
        }
        if(gate) {
            try { // This try-catch's aim is checking customer id is valid or invalid.
                customer_id = customers[Integer.valueOf(splitted[2])].getCustomer_id(); //Using this way to get exceptions if exists.
            }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){
                System.out.println("    ERROR ! Invalid customer id!"); gate = false; // If got an exception, gate will be locked for further steps. Object wont be added.
                countContract++;
            }
            if(gate){
                try { // This try-catch's aim is checking agent id is valid or invalid.
                    agent_id = agents[Integer.valueOf(splitted[3])].getAgent_id();
                }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){
                    System.out.println("    ERROR ! Invalid agent id!");gate =false;
                    countContract++;
                }
                if(gate){
                    contracts[countContract] = new Contract(countContract,real_estate_id,customer_id,agent_id, temp_contract_date);
                    countContract++;
                    String status_temp = realEstates[Integer.valueOf(splitted[1])].getStatus();
                    double price_temp = realEstates[Integer.valueOf(splitted[1])].getPrice();
                    agents[Integer.valueOf(splitted[3])].moneyOperations(status_temp,price_temp,splitted[4]);
                    System.out.println("    Contract successfully added.");
                }
            }
        }
    }


    public void deleteAgent(String[] splitted) {
        try{
            System.out.println("\n-----> DELETE AGENT");
            int agent_id = agents[Integer.valueOf(splitted[1])].getAgent_id(); //To get exceptions if exists.
            if (agents[agent_id].getFlag()){ //If its flag is true.
                System.out.println("    Agent "+agents[agent_id].getName()+" is successfully deleted!");
                agents[agent_id].delete(); //Then its flag will false
            }
            else System.out.println("    ERROR ! This agent has been already deleted!");
        }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("    ERROR ! Invalid agent id!");
        }
    }

    public void deleteRealEstate(String[] splitted) {
        try{
            System.out.println("\n-----> DELETE REAL ESTATE");
            int real_estate_id = realEstates[Integer.valueOf(splitted[1])].getReal_estate_id();
            if (realEstates[real_estate_id].getFlag()) {
                System.out.println("    Real Estate "+real_estate_id+" is successfully deleted!");
                realEstates[real_estate_id].delete();
            }
            else System.out.println("    ERROR ! This real estate has been already deleted!");
        }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("    ERROR ! Invalid real estate id!");
        }
    }

    public void deleteCustomer(String[] splitted){
        try{
            System.out.println("\n-----> DELETE CUSTOMER");
            int customer_id = customers[Integer.valueOf(splitted[1])].getCustomer_id();
            if (customers[customer_id].getFlag()) {
                System.out.println("    Customer "+customers[customer_id].getName()+" is successfully deleted!");
                customers[customer_id].delete();
            }
            else System.out.println("    ERROR ! This customer has been already deleted!");
        }catch(java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("    ERROR ! Invalid customer id!");
        }

    }


    public void displayAgencies() {
        System.out.println("\n-----> AGENCIES\n");
        for(int i=1;i<countAgency;i++) {
            if(agencies[i] != null) { // Unless that object is null.
                agencies[i].displayAgency();
            }
        }
    }

    public void displayAgents(){
        System.out.println("\n-----> AGENTS\n");
        for(int i=1;i<countAgent;i++){
            if(agents[i] != null && agents[i].getFlag()) {
                agents[i].displayAgent();
            }
        }
    }

    public void displayRealEstates() {
        System.out.println("\n-----> REAL ESTATES\n");
        for (int i = 1; i < countRealEstate; i++) {
            if (realEstates[i] != null && realEstates[i].getFlag()) {
                realEstates[i].displayRealEstate();
            }
        }
    }

    public void displayCustomers(){
        System.out.println("\n-----> CUSTOMERS\n");
        for(int i=1;i<countCustomer;i++) {
            if (customers[i] != null && customers[i].getFlag()) {
                customers[i].displayCustomer();
            }
        }
    }

    public void displayContracts(){
        System.out.println("\n-----> CONTRACTS\n");
        for(int i=1;i<countContract;i++) {
            if(contracts[i] != null) {
                contracts[i].displayContract();
            }
        }
    }




    public void search(String[] splitted){
        System.out.println("\n-----> SEARCH\n");
        System.out.print("   CRITERIAS : ");
        for(int i=1;i<splitted.length;i++) System.out.print(splitted[i]+","); //These lines for visualize.
        System.out.println("\n");

        int i=0,j=0,search_count=0;
        int[][] search_array = new int[countRealEstate][8]; //For search method, creating an 2-d integer array.
        // 0. Index -> Real Estate ID
        // 1. Index -> 1. criteria
        // 2. Index -> 2. criteria ...

        //In array, 0 means not suitable, 1 means suitable.

        for(i=0;i<search_array.length;i++){
            for(j=0;j<search_array[0].length;j++){
                search_array[i][j]=0;}}

        for(i=1;i<countRealEstate;i++){
            if(realEstates[i] != null) {
                search_array[i][0] = realEstates[i].getReal_estate_id();
                String[] minmax_surface_area = splitted[5].split("-", -1);
                String[] minmax_price = splitted[6].split("-", -1);
                String[] minmax_room_number = splitted[7].split("-", -1);

                if (realEstates[i].getFlag()) {
                    if (splitted[1].equals("")) search_array[i][1] = 1; // If there is no criteria, all real estates included.
                    else if (realEstates[i].getType().equals(splitted[1])) search_array[i][1] = 1; // If there is a criteria, only suitable real estates included.

                    if (splitted[2].equals("")) search_array[i][2] = 1;
                    else if (realEstates[i].getStatus().equals(splitted[2])) search_array[i][2] = 1;

                    if (splitted[3].equals("")) search_array[i][3] = 1;
                    else if (realEstates[i].getTown().equals(splitted[3])) search_array[i][3] = 1;

                    if (splitted[4].equals("")) search_array[i][4] = 1;
                    else if (realEstates[i].getCity().equals(splitted[4])) search_array[i][4] = 1;

                    if (splitted[5].equals("")) search_array[i][5] = 1;
                    else if (realEstates[i].getSurface_area() > Double.valueOf(minmax_surface_area[0]) && realEstates[i].getSurface_area() < Double.valueOf(minmax_surface_area[1]))
                        search_array[i][5] = 1;

                    if (splitted[6].equals("")) search_array[i][6] = 1;
                    else if (realEstates[i].getPrice() > Double.valueOf(minmax_price[0]) && realEstates[i].getPrice() < Double.valueOf(minmax_price[1]))
                        search_array[i][6] = 1;

                    if (splitted[7].equals("")) search_array[i][7] = 1;
                    else if (realEstates[i].getNumber_of_rooms() > Integer.valueOf(minmax_room_number[0]) && realEstates[i].getNumber_of_rooms() < Integer.valueOf(minmax_room_number[1]))
                        search_array[i][7] = 1;
                }
            }
        }

        for(i = 0;i<search_array.length;i++){
            String search_temp = "";
            if(search_array[i] != null) {
                for (j = 1; j < search_array[0].length; j++) {
                    search_temp += search_array[i][j];
                }
                if (search_temp.equals("1111111")) { // If all criterias suitable for that real estate.
                    realEstates[i].displayRealEstate();
                    search_count++;
                }
            }
        }
        if(search_count == 0) System.out.println("    No results found!");
        else System.out.println("    "+search_count+" result found!");
    }

    // For 4 methods below, Obtained date input is changed to "last 2 digits of year"+"month". For example 1901 means 01/2019.
    // These transformation is using in agent class.

    public void calculateSalaries(String[] splitted) {
        System.out.println("\n-----> SALARIES\n");
        System.out.println("   Date = "+splitted[1]+"\n");
        int date = Integer.valueOf(splitted[1].substring(5)+splitted[1].substring(0,2));
        for(int i=1;i<countAgent;i++){ // For obtained dated, Listing salary of agents.
            if(agents[i] != null && agents[i].getFlag()) {
                System.out.println("    AGENT " + agents[i].getAgent_id() + " - " + agents[i].getName());
                System.out.println("    Salary = " +agents[i].getSalary(date)+ " TL\n");
            }
        }
    }

    public void calculateTotalIncome(String[] splitted){
        System.out.println("\n-----> TOTAL INCOME\n");
        int date = Integer.valueOf(splitted[1].substring(5)+splitted[1].substring(0,2));
        double total_income = 0;
        for(int i=1;i<countAgent;i++){
            if(agents[i] != null && agents[i].getFlag()) {
                double[][] temp_history = agents[i].agent_history; // An array which holds agent properties about money in specific time period. That time period is defined in agent class.
                for (int j = 0; j < temp_history.length; j++) {
                    if (temp_history[j][0] == date) { // Listing for obtained date.
                        total_income += temp_history[j][2];
                    }
                }
            }
        }
        System.out.println("    Total income = "+(int)total_income+" TL");
    }

    public void mostProfitableAgency(String[] splitted){
        System.out.println("\n-----> MOST PROFITABLE AGENCY\n");
        int date = Integer.valueOf(splitted[1].substring(5)+splitted[1].substring(0,2));
        double[][] agencies_and_incomes = new double[countAgency][2]; //Index 0 = Agency ID , Index 1 = Total income of that month.
        for(int i=1;i<countAgent;i++){
            if(agents[i] != null && agents[i].getFlag()) {
                double[][] temp_history = agents[i].agent_history;
                for (int j = 0; j < temp_history.length; j++) {
                    if (temp_history[j][0] == date) {
                        agencies_and_incomes[(agents[i].getAgency_id())][1] += temp_history[j][2]; // Getting agents earned money for her/his office and adding that money to office's index.
                    }
                }
            }
        }
        //To get maximum value of array. Code in below.
        int most = 1;
        double temp = agencies_and_incomes[1][1];
        for(int i=2;i<agencies_and_incomes.length;i++){
            if(agencies_and_incomes[i][1]>temp){
                temp = agencies_and_incomes[i][1];
                most = i;
            }
        }
        System.out.println("    "+agencies[most].getName());
        System.out.println("    "+(int)agencies_and_incomes[most][1]+" TL");
    }

    public void agentOfTheMonth(String[] splitted){
        System.out.println("\n-----> AGENT OF THE MONTH\n");
        int date = Integer.valueOf(splitted[1].substring(5)+splitted[1].substring(0,2));
        double max_salary=0;
        int index=0,index_of_date=0;
        for(int i=1;i<countAgent;i++){
            if(agents[i] != null && agents[i].getFlag()) {
                double[][] temp_history = agents[i].agent_history;
                for (int j = 0; j < temp_history.length; j++) {
                    if (temp_history[j][0] == date) {
                        if (temp_history[j][1] >= max_salary) { //Getting the highest salary in agent's history on obtained date.
                            max_salary = temp_history[j][1];
                            index = i;
                            index_of_date = j;
                        }
                    }
                }
            }
        }
        double[][] temp_agent_history = agents[index].agent_history;
        int sales = (int)temp_agent_history[index_of_date][3];
        int rental = (int)temp_agent_history[index_of_date][4];
        //Code in below for visualize.
        if(sales > 0 && rental > 0) System.out.println("    "+agents[index].getName()+" with "+sales+" sales and "+rental+" rentals from "+agencies[agents[index].getAgency_id()].getName());
        else if(sales > 0) System.out.println("    "+agents[index].getName()+" with "+sales+" sales from "+agencies[agents[index].getAgency_id()].getName());
        else System.out.println("    "+agents[index].getName()+" with "+rental+" rentals from "+agencies[agents[index].getAgency_id()].getName());


    }

    public boolean checkDate(String date) {
        //Checking date is valid or invalid by using if/else statements.
        date = date.replace(" ", "");
        String[] temp_date = date.split("/");
        int day = Integer.valueOf(temp_date[0]);
        int month = Integer.valueOf(temp_date[1]);
        int year = Integer.valueOf(temp_date[2]);

        if(month == 1 && (day>31 ||day<1)) return false;
        else if(month == 2 && ((year%4 == 0 &&(day>29 ||day<1)) || (year%4 != 0 && (day>28 || day<1)))) return false;
        else if(month == 3 && (day>31 ||day<1)) return false;
        else if(month == 4 && (day>30 ||day<1)) return false;
        else if(month == 5&& (day>31 ||day<1)) return false;
        else if(month == 6 && (day>30 ||day<1)) return false;
        else if(month == 7 && (day>31 ||day<1)) return false;
        else if(month == 8 && (day>31 ||day<1)) return false;
        else if(month == 9 && (day>30 ||day<1)) return false;
        else if(month == 10 && (day>31 ||day<1)) return false;
        else if(month == 11 && (day>30 ||day<1)) return false;
        else if(month == 12 && (day>31 ||day<1)) return false;
        else return true;
    }
}