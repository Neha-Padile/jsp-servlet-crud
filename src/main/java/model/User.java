package model;

public class User {

    private int id;
    private String name;
    private  String address;
    private int pincode;

    public User() {
    }

    public User(int id, String name, String address, Integer pincode) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.pincode = pincode;
    }

    public User(String name, String address, int pincode) {
        super();
        this.name = name;
        this.address = address;
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pincode=" + pincode +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
