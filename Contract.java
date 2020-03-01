public class Contract
{
    private int contract_id;
    private int real_estate_id;
    private int customer_id;
    private int agent_id;
    private Date contract_date;

    public Contract(int contract_id,int real_estate_id,int customer_id, int agent_id, Date contract_date) {
        this.contract_id=contract_id;
        this.real_estate_id = real_estate_id;
        this.customer_id = customer_id;
        this.agent_id = agent_id;
        this.contract_date = contract_date;
    }

    public void displayContract() {
        System.out.println("   CONTRACT "+contract_id);
        System.out.println("    Real Estate ID = "+real_estate_id);
        System.out.println("    Customer ID = "+customer_id);
        System.out.println("    Agent ID = "+agent_id);
        System.out.println("    Contract Date = "+contract_date.toString()+"\n");
    }

}