package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {

    /**
     * <p>
     * This class represents the 'Observable' side in the <b><i>Observer</i></b> Design Pattern.
     * This class registers members to itself and holds an <b><i>UndoableStringBuilder</i></b> variable.
     * When a member registers to this <b><i>GroupAdmin</i></b> member list, his <b><i>UndoableStringBuilder</i></b> field variable
     * points to the same <b><i>UndoableStringBuilder</i></b> field variable of this <b><i>GroupAdmin</i></b> ('Shallow Copy').
     * </p>
     * Therefor every time we manipulate this <b><i>GroupAdmin UndoableStringBuilder</i></b>,
     * all of his members points to this <b><i>UndoableStringBuilder</i></b> and will be aware to the changes that being made.
     *
     * @param members an ArrayList that holds this GroupAdmin members
     * @param actionsPerMember an ArrayList that counts how many actions each member made
     * @param usb is an UndoableStringBuilder that the members points to
     * @param name is a String that indicates of the name of this GroupAdmin
     */
    private final ArrayList<Member> members;

    private final ArrayList<Integer> actionsPerMember;

    private final UndoableStringBuilder usb;

    private final String name;

    /**
     * The following constructor constructs a new <b><i>GroupAdmin</i></b> object with the given name,
     * it initializes the <b><i>members</i></b>, <b><i>actionsPerMember</i></b> and the <b><i>usb</i></b> fields.
     *
     * @param name is a String that indicates of the name of this GroupAdmin
     */
    public GroupAdmin(String name) {
        members = new ArrayList<>();
        actionsPerMember = new ArrayList<>();
        usb = new UndoableStringBuilder();
        this.name = name;
    }

    /**
     * Empty Constructor
     */
    public GroupAdmin(){
        members = new ArrayList<>();
        actionsPerMember = new ArrayList<>();
        usb = new UndoableStringBuilder();
        name = "";
    }

    /**
     * <p>
     * A method that takes a Member object and adds it to the member list (members)
     * of this GroupAdmin if he is not already in there.
     * </p>
     * <p>
     * When a Member register, the actionsPerMember list adds to its list the integer 0 which reference that
     * this added Member was aware to 0 actions that the GroupAdmin made.
     *</p>
     * <p>
     * When a Member register, a message also is printed indicating that the Member has been registered successfully.
     * </p>
     * This method uses the <b><i>notifyMembers()</i></b> method so these member's UndoableStringBuilder
     * will point to the same UndoableStringBuilder as this GroupAdmin's.
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

            actionsPerMember.add(0);

            System.out.println("Member registered successfully.");
        }

        else {
            System.out.println("This Member is already register!!!!");
        }
    }

    /**
     * <p>
     * This method unregisters a Member from the GroupAdmin.
     * If the given Member is not registered with the GroupAdmin, a message is printed indicating this.
     * </p>
     * <p>
     * Otherwise, the Member is removed from the registered members list,
     * and a message is printed indicating that the Member has been unregistered successfully.
     * </p>
     * Once a member unregisters, his actions counter indication in the actionsPerMember list removed as well.
     * then an update is being sent to him with a new copy of the GroupAdmin's UndoableStringBuilder, so he will no more
     * keep track of the changes being made in the GroupAdmin, but he will be aware to the changes that has being made
     * while he was a member.
     *
     * @param obj the Member to be unregistered.
     */
    @Override
    public void unregister(Member obj) {
        if(members.contains(obj)){
            int index = members.indexOf(obj);
            members.remove(index);

            obj.update(new UndoableStringBuilder(this.usb, actionsPerMember.get(index)));
            actionsPerMember.remove(index);

            System.out.println("Member unregister successfully");
        }

        else{
            System.out.println("This Member is not register!!!!");
        }

    }

    /**
     * <p>
     * This method inserts a String in an offset index and pushes the rest of the original String to appear
     * at the end of the given String parameter.
     * </p>
     * This method also increases each member action counter by 1.
     *
     * @param offset int starting index
     * @param obj String to insert
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        notifyMembers();
        for(int i = 0; i < actionsPerMember.size(); i++){
            actionsPerMember.set(i, actionsPerMember.get(i) + 1);
        }
    }

    /**
     * This method Appends a string to the UndoableStringBuilder object associated with the GroupAdmin.
     * This method also increases each member action counter by 1.
     *
     * @param obj the String to be appended.
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyMembers();
        for(int i = 0; i < actionsPerMember.size(); i++){
            actionsPerMember.set(i, actionsPerMember.get(i) + 1);
        }
    }

    /**
     * <p>
     * This method deletes a subString according to the given indexes from the UndoableStringBuilder object associated with the GroupAdmin.
     * </p>
     * This method also increases each member action counter by 1.
     *
     * @param start an int of a starting index
     * @param end an int of an ending index
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        notifyMembers();
        for(int i = 0; i < actionsPerMember.size(); i++){
            actionsPerMember.set(i, actionsPerMember.get(i) + 1);
        }
    }

    /**
     * This method Performs an undo operation on the
     * UndoableStringBuilder object associated with the GroupAdmin.
     * This method also decreases each member action counter by 1.
     */
    @Override
    public void undo() {
        usb.undo();
        notifyMembers();
        for(int i = 0; i < actionsPerMember.size(); i++){
            actionsPerMember.set(i, actionsPerMember.get(i) - 1);
        }
    }

    /**
     * <p>
     * This method replace a subString according to the given indexes, with a given String
     * from the UndoableStringBuilder object associated with the GroupAdmin.
     * </p>
     * This method also increases each member action counter by 1.
     *
     * @param start an int of a starting index
     * @param end an int of an ending index
     * @param str the String to replace to
     */
    public void replace(int start, int end, String str){
        usb.replace(start, end, str);
        notifyMembers();
        for(int i = 0; i < actionsPerMember.size(); i++){
            actionsPerMember.set(i, actionsPerMember.get(i) + 1);
        }
    }

    /**
     * This method allows us to get this UndoableStringBuilder
     * to use for our Tests.
     *
     * @return this UndoableStringBuilder
     */
    public UndoableStringBuilder getUsb(){
        return usb;
    }

    /**
     * This method allows us to get this members list to use for our Tests.
     *
     * @return this ArrayList of members
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * This method allows us to get this GroupAdmin's name to use for our Tests.
     *
     * @return this GroupAdmin's name
     */
    public String getName() {
        return name;
    }

    public void printMembers(){

        String membersList = name + " members:\n";
        for(int i = 0; i < members.size(); i++){
            membersList += "" + (i+1) + ") " + members.get(i) + "\n";
        }

        System.out.println(membersList);
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