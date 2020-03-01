public class Agent {
    private boolean flag = true;
    private int agent_id,agency_id;
    private String name,gender;
    private Date birthdate;
    private Address address;
    private Phone phone;
    //Extra variables for money calculating.
    double[] dates = {1810,1811,1812,1901,1902,1903,1904,1905,1906,1907,1908,1909,1910,1911,1912,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012}; //Specific date period for recording.
    double[][] agent_history = new double[dates.length][5]; // 0. Index -> date , 1. Index -> salary, 2. Index -> earned money for his/her office, 3. Index sales number, 4. Index rental number.

    public Agent(int agent_id,int agency_id,String name,Date birthdate,Address address,Phone phone,String gender) {
        this.agent_id=agent_id;
        this.agency_id=agency_id;
        this.name=name;
        this.birthdate=birthdate;
        this.address=address;
        this.phone=phone;
        this.gender=gender;
        for(int i=0;i<agent_history.length;i++){
            this.agent_history[i][0] = dates[i];}
    }
    public String getName() {
        return name;
    }
    public int getAgent_id(){ return agent_id; }
    public int getAgency_id(){ return agency_id;}
    public boolean getFlag(){return flag;}

    public double getSalary(int date){
        double salary =2000; // Default salary.
        for(int i=0;i<agent_history.length;i++){
            if(agent_history[i][0] == date){
                salary += agent_history[i][1]; //Returning the agent's salary on specific date.
            }
        }
        return salary;
    }

    public void moneyOperations(String status,double price,String date_line){
        int date = Integer.valueOf(date_line.substring(8)+date_line.substring(3,5));
        double fee=0,income=0;
        if(status.equals("For Sale")){ //Updating agent's history for sale contract.
            fee = (price*5)/100; // %5 for agent
            income = fee*3; // %15 for office
            for(int i=0;i<agent_history.length;i++){
                if(agent_history[i][0] == date){
                    agent_history[i][1] += fee;
                    agent_history[i][2] += income;
                    agent_history[i][3]++;
                }
            }
        }
        else{ //Updating agent's history for rental contract.
            fee = (price*20)/100; // %20 for agent
            income = fee*4; // %80 for office
            for(int i=0;i<agent_history.length;i++){
                if(agent_history[i][0] == date){
                    agent_history[i][1] += fee;
                    agent_history[i][2] += income;
                    agent_history[i][4]++;
                }
            }

        }

    }

    public void delete() {
        this.flag = false;
    }

    public void displayAgent() {
        System.out.println("   AGENT "+agent_id);
        System.out.println("    AgencyID = "+agency_id);
        System.out.println("    Name = "+name);
        System.out.println("    Gender = "+gender);
        System.out.println("    Birthdate = "+birthdate.toString());
        System.out.println("    Address = "+address.toString());
        System.out.println("    Phone = "+phone.toString()+"\n");
    }
}