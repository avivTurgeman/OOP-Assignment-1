package observer;

/**
 * Yasmin
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
     * Yasmin
     * @param usb
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * Yasmin
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