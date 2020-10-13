package CS3230_Project;

public class Booking {
    private String name;
    private String numRes;
    private String checkInDate;
    private String checkOutDate;
    private String roomNum;
    public Booking(String name, String numRes, String checkInDate, String checkOutDate, String roomNum)
    {
        setName(name);
        setNumRes(numRes);
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        setRoomNum(roomNum);
    }
    public void setName(String nameOb)
    {
        this.name = nameOb;
    }
    public void setNumRes(String numResOb) {
        if (Integer.valueOf(numResOb)>=1 && Integer.valueOf(numResOb)<=9)
            this.numRes = numResOb;
    }
    public void setCheckInDate(String checkInDateOb) {
        int intCheckInDateOb = Integer.valueOf(checkInDateOb);
        if (intCheckInDateOb<=31 && intCheckInDateOb>=1)
            this.checkInDate = checkInDateOb;
    }
    public void setCheckOutDate(String checkOutDateOb) {
        int intCheckOutDateOb = Integer.valueOf(checkOutDateOb);
        if (intCheckOutDateOb<=31 && intCheckOutDateOb>=1)
            this.checkOutDate = checkOutDateOb;
    }
    public void setRoomNum(String roomNumOb) {
        int intRoomNumOb = Integer.valueOf(roomNumOb);
        if (intRoomNumOb<=500 && intRoomNumOb>=1)
            this.roomNum = roomNumOb;
    }
    public String getName()
    {
        return name;
    }
    public String getNumRes()
    {
        return numRes;
    }
    public String getCheckInDate()
    {
        return checkInDate;
    }
    public String getCheckOutDate()
    {
        return checkOutDate;
    }
    public String getRoomNum()
    {
        return roomNum;
    }
}