package observer;

public class ConcreteMember implements Member {
    /**
     * Concrete implementation of the Member interface.
     * Represents a Member of a group administered by a GroupAdmin.
     * Can register and receive updates from a GroupAdmin.
     * A ConcreteMember has a name and a reference to a GroupAdmin,
     * and maintains its own copy of the group's UndoableStringBuilder object pointer.
     */

    private UndoableStringBuilder usb;
    private Sender groupAdmin;
    private String name;


    /**
     * <p>
     * a Constructor that set this <b><u>Sender</u></b> groupAdmin to a new
     * given <b><i>Sender</i></b> parameter
     * </p>
     * This Constructor also set the <b><i>name</i></b> of this <b><i>ConcreteMember</i></b>.
     *
     * @param groupAdmin a Sender
     * @param name a String
     */
    public ConcreteMember(Sender groupAdmin, String name) {

        this.name = name;

        this.groupAdmin = groupAdmin;

        usb = new UndoableStringBuilder();

        groupAdmin.register(this);

    }

    /**
     * Empty Constructor
     */
    public ConcreteMember(){
        usb = new UndoableStringBuilder();
        groupAdmin = new GroupAdmin();
        name = "";
    }

    /**
     * This method updates the UndoableStringBuilder object of the ConcreteMember
     * with the given UndoableStringBuilder object.
     *
     * @param usb the new UndoableStringBuilder object
     */
    @Override
    public void update(UndoableStringBuilder usb) {

        this.usb = usb;
    }

    /**
     * This method reference to the String of this UndoableStringBuilder
     * to use for our tests.
     *
     * @return this UndoableStringBuilder String value
     */
    public String printUSB(){
            return usb.toString();
    }

    /**
     * This method allows us to get this UndoableStringBuilder
     * to use for our tests.
     *
     * @return this UndoableStringBuilder
     */
    public UndoableStringBuilder getUsb(){
        return usb;
    }

}