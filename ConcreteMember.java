package observer;

/**
 * Concrete implementation of the Member interface.
 * Represents a Member of a group administered by a GroupAdmin.
 * Can register and receive updates from a GroupAdmin.
 * A ConcreteMember has a name and a reference to a GroupAdmin,
 * and maintains its own copy of the group's UndoableStringBuilder object.
 */
public class ConcreteMember implements Member {

    private UndoableStringBuilder usb;
    private Sender groupAdmin;
    private String name;

    /**
     * Aviv
     * @param groupAdmin
     * @param name
     */
    public ConcreteMember(Sender groupAdmin, String name) {

        this.name = name;

        this.groupAdmin = groupAdmin;

        groupAdmin.register(this);

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
     * This method prints the current state of the ConcreteMember's UndoableStringBuilder object to the console.
     */
    public void printUSB(){
        System.out.println(name + ": " + usb.toString());
    }

    /**
     * Aviv
     */
    public void print_USB_IdentityHashCode(){
        System.out.println(name + " usb identity hash code is: " + System.identityHashCode(usb));
    }

    /**
     * Aviv
     * @return
     */
    public String getName(){
        return name;
    }

}