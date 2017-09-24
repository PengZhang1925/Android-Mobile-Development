package socialevent.model;

/**
 * Created by user on 2016/9/23.
 */
public class eventListModel {
    private String ID = "";
    private String eventName = "";
    private String startDate = "";
    private String endDate = "";
    private String startTime = "";
    private String endTime = "";
    private String venue = "";
    private Double latitude = 0.0;
    private Double longtitude = 0.0;
    private String notes = "";
    private String contactList = "";

    public void setID(String ID){
        this.ID = ID;
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public void setVenue(String venue){
        this.venue = venue;
    }
    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }
    public void setLongtitude(Double longtitude){
        this.longtitude = longtitude;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setContactList(String contactList){
        this.contactList = contactList;
    }

    public String getID(){
        return this.ID;
    }
    public String getEventName(){
        return this.eventName;
    }
    public String getStartDate(){
        return this.startDate;
    }
    public String getEndDate(){
        return this.endDate;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public String getVenue(){
        return this.venue;
    }
    public Double getLatitude(){
        return this.latitude;
    }
    public Double getLongtitude(){
        return this.longtitude;
    }
    public String getNotes(){
        return this.notes;
    }
    public String getContactList(){
        return this.contactList;
    }
}
