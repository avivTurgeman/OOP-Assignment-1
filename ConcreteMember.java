package observer;

public class ConcreteMember implements Member {

    private UndoableStringBuilder usb;
    private Sender groupAdmin;
    private String name;

    public ConcreteMember(Sender groupAdmin, String name) {

        this.name = name;

        this.groupAdmin = groupAdmin;

        groupAdmin.register(this);

    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    public void printUSB(){
        System.out.println(name + ": " + usb.toString());
    }

    public void print_USB_IdentityHashCode(){
        System.out.println(name + " usb identity hash code is: " + System.identityHashCode(usb));
    }

    public String getName(){
        return name;
    }

}