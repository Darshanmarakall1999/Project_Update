package PessengerPack;

public class Passenger implements Comparable<Passenger> {
    private String name;
    private int age;
    private char gender;

    public Passenger(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }


    @Override
    public String toString() {
        return "\r\n"
                + name +
                "\t\t" + age +
                "\t\t" + gender+"\t\t";
    }


    @Override
    public int compareTo(Passenger o) {
        if((o.getName()).equals(name))
        {
            return 0;
        }
        else
        {
            if (((o.getName()).compareTo(name))>0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
}