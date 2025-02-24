package ChatApp;

import java.util.HashMap;

public class Chatroom {
    private String RoomName ;
    private member_info leader ; 
    private final HashMap<String , member_info> MembersArr;

    public Chatroom(String RoomName , member_info leader){
        this.RoomName = RoomName;
        this.leader = leader;
        this.MembersArr = new HashMap<>(); //each creator have it's own array of members
        leader.SetRole("leader");
        MembersArr.put(leader.getname(), leader);
    }
}
