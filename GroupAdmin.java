package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {

    /**
     * This class represents the 'Observable' side in the Observer Design Pattern.
     * This class registers members to itself and holds an UndoableStringBuilder variable.
     * When a member registers to this GroupAdmin, his UndoableStringBuilder field variable points to the same UndoableStringBuilder
     * field variable of this GroupAdmin ('Shallow Copy') therefor every time we manipulate this GroupAdmin UndoableStringBuilder,
     * all of his members points to this UndoableStringBuilder and will be aware of the changes that being made.
     *
     * @param members is an ArrayList that holds this GroupAdmin members
     * @param usb is an UndoableStringBuilder that the members points to
     * @param name is a String that indicates of the name of this GroupAdmin
     */
    private final ArrayList<Member> members;
    private UndoableStringBuilder usb;

    private final String name;

    /**
     * The following constructor constructs a new GroupAdmin object with the given name,
     * it initializes the members and usb fields.
     * @param name the name of the GroupAdmin.
     */
    public GroupAdmin(String name) {
        members = new ArrayList<Member>();
        usb = new UndoableStringBuilder();
        this.name = name;
    }

    /**
     * A method that takes a Member object and adds it to the member list (members)
     * of this GroupAdmin if he is not already in there.
     * this method uses the <b><i>notifyMembers()</i></b> method to make this member's UndoableStringBuilder
     * to point to the same UndoableStringBuilder of this GroupAdmin.
     * <p>
     * once the member is register, this method will print out:
     * <p>
     * <b>"Member number -> <i>{member number in members}</i> added to <i>{GroupAdmin's name}</i>."</b>
     * </p>
     * </p>
     * <p>
     * if a member is already register, this method will print out:
     * <p>
     * "This Member is already register."
     * </p>
     * </p>
     * @param obj a Member object to register
     */
    @Override
    public void register(Member obj) {
        if(!members.contains(obj)){
            members.add(obj);

            int index = members.indexOf(obj);

            notifyMembers();

            System.out.println("Member number -> " + (index + 1) + ", added to " + name + ".");

        }

        else {
            System.out.println("This Member is already register.");
        }
    }

    /**
     * This method unregisters a Member from the GroupAdmin.
     * If the given Member is not registered with the GroupAdmin a message is printed indicating this.
     * Otherwise, the Member is removed from the list of registered members and
     * a message is printed indicating that the Member has been removed.
     * @param obj the member to be unregistered.
     */
    @Override
    public void unregister(Member obj) {
        if(members.contains(obj)){
            int index = members.indexOf(obj);
            members.remove(index);
            System.out.println("Member number -> " + (index + 1) + ", removed from " + name + ".");
        }

        else{
            System.out.println("This Member is not register.");
        }

    }

    /**
     * Aviv
     * @param offset
     * @param obj
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);

    }

    /**
     * This method Appends a string to the UndoableStringBuilder object associated with the GroupAdmin.
     * @param obj the string to be appended.
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
    }

    /**
     * Aviv
     * @param start
     * @param end
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
    }

    /**
     * This method Performs an undo operation on the
     * UndoableStringBuilder object associated with the GroupAdmin.
     */
    @Override
    public void undo() {
        usb.undo();
    }

    /**
     * Aviv
     * @param start
     * @param end
     * @param str
     */
    public void replace(int start, int end, String str){
        usb.replace(start, end, str);
    }

    /**
     * Notifies all registered Members of the GroupAdmin of an update to the UndoableStringBuilder object.
     * The update method of each Member is called with the current state of the
     * UndoableStringBuilder object as an argument.
     */
    public void notifyMembers() {
        for (Member m : members) {
            m.update(usb);
        }
    }

}