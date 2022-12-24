package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {

    private ArrayList<Member> members;
    private UndoableStringBuilder usb;

    public GroupAdmin() {
        members = new ArrayList<Member>();
        usb = new UndoableStringBuilder();
    }

    @Override
    public void register(Member obj) {
        members.add(obj);

        int index = members.indexOf(obj);

        System.out.println("Member number -> " + (index + 1) + ", added");
    }

    @Override
    public void unregister(Member obj) {
        int index = members.indexOf(obj);
        System.out.println("Member number -> " + (index + 1) + ", deleted");
    }

    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        notifyMembers();
    }

    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyMembers();
    }

    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        notifyMembers();
    }

    @Override
    public void undo() {
        usb.undo();
        notifyMembers();
    }

    public void notifyMembers() {
        for (Member m : members) {
            m.update(usb);
        }
    }

    public void setUndoableStringBuilder(UndoableStringBuilder usb) {
        this.usb = usb;
        notifyMembers();
    }

    // public String toString() {
    // for (Member m : members) {
    // System.out.println("" + (members.indexOf(m) + 1) + ": ");
    // }
    // return "";
    // }

}
