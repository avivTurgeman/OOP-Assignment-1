package observer;

import java.util.ArrayList;

/**
 * (I will finish this!!!!!!!!!!!!!!!!!!)
 *
 * This class represents the 'Observable' side in the Observer Design Pattern.
 * Which means that this class registers members to itself and holds an UndoableStringBuilder variable.
 * Each member points to this class UndoableStringBuilder variable so that each time this UndoableStringBuilder
 * changes, this class will inform every one of her members about it and update their UndoableStringBuilder in accordance.
 *
 */
public class GroupAdmin implements Sender {

    private final ArrayList<Member> members;
    private UndoableStringBuilder usb;

    private final String name;

    /**
     * Yasmin
     * @param name
     */
    public GroupAdmin(String name) {
        members = new ArrayList<Member>();
        usb = new UndoableStringBuilder();
        this.name = name;
    }

    /**
     * Aviv
     * @param obj
     */
    @Override
    public void register(Member obj) {
        members.add(obj);

        int index = members.indexOf(obj);

        System.out.println("Member number -> " + (index + 1) + ", added to " + name);
    }

    /**
     * Yasmin
     * @param obj
     */
    @Override
    public void unregister(Member obj) {
        int index = members.indexOf(obj);
        System.out.println("Member number -> " + (index + 1) + ", removed from " + name);
    }

    /**
     * Aviv
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        notifyMembers();
    }

    /**
     * Yasmin
     * @param obj
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyMembers();
    }

    /**
     * Aviv
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        notifyMembers();
    }

    /**
     * Yasmin
     */
    @Override
    public void undo() {
        usb.undo();
        notifyMembers();
    }

    /**
     * Aviv
     * @param start
     * @param end
     * @param str
     */
    public void replace(int start, int end, String str){
        usb.replace(start, end, str);
        notifyMembers();
    }

    /**
     * Yasmin
     */
    public void notifyMembers() {
        for (Member m : members) {
            m.update(usb);
        }
    }

    /**
     * Aviv
     * @param usb
     */
    public void setUndoableStringBuilder(UndoableStringBuilder usb) {
        this.usb = usb;
        notifyMembers();
    }

}