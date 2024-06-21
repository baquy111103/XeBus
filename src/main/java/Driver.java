public class Driver {
    public static int nextDriverID = 10000;
    private int driverID;
    private String fullname;
    private String address;
    private String phoneNumber;
    private String qualification;

    public Driver(String fullname, String address, String phoneNumber, String qualification){
        this.driverID = nextDriverID++;
        this.fullname = fullname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.qualification = qualification;
    }

    public int getDriverID() {
        return driverID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString(){
        return driverID +" - " + fullname +" - " + phoneNumber + " - "+qualification;
    }
}
