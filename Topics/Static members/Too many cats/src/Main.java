
class Cat {

    // write static and instance variables
    public String name;
    public int age;

    public static int counter = 0;
    public static final int MAX_CATS = 5;

    public Cat(String name, int age) {
        // implement the constructor
        // do not forget to check the number of cats
        this.name = name;
        this.age = age;
        counter++;
        if (counter > MAX_CATS) {
            System.out.println("You have too many cats");
        }
    }

    public static int getNumberOfCats() {
        // implement the static method
        return counter;
    }
}