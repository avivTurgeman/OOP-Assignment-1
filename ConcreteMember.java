package observer;

public class ConcreteMember implements Member {

    private UndoableStringBuilder usb;
    private Sender groupAdmin;
    private static int memberIDTracker = 0;
    private int memberID;

    public ConcreteMember(Sender groupAdmin) {

        this.groupAdmin = groupAdmin;

        this.memberID = ++memberIDTracker;

        groupAdmin.register(this);

    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
        System.out.println("member No." + memberID + " updated.");
    }

    public void getUSB() {
        System.out.println(usb.toString());
    }
}
