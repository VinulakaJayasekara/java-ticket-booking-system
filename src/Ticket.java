public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void print() {
        System.out.println("Person email: " + person.getEmail());
        System.out.println(person.getName()+" "+person.getSurname()+" successfully purchased row "+row+" seat "+seat+" for £"+price);
    }
    public Person getPerson(){
        return person;
    }
    public int getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice() {
        return price;
    }
}
