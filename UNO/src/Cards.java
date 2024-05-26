public class Cards {
    int number;
    CardColors color;
    CardType type;

    public Cards(int number, CardColors color, CardType type) {
        this.number = number;
        this.color = color;
        this.type = type;
    }


    public String toString(){
       return number + " " + color + " " + type;
    }
}