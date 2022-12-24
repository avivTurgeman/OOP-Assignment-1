package observer;

public class main {
    public static void main(String[] args) {

        GroupAdmin Gadmin = new GroupAdmin();

        ConcreteMember member1 = new ConcreteMember(Gadmin);
        ConcreteMember member2 = new ConcreteMember(Gadmin);

        UndoableStringBuilder usb1 = new UndoableStringBuilder("usb1");
        UndoableStringBuilder usb2 = new UndoableStringBuilder("usb2");
        UndoableStringBuilder usb3 = new UndoableStringBuilder("usb3");
        UndoableStringBuilder usb4 = new UndoableStringBuilder("usb4");

        Gadmin.setUndoableStringBuilder(usb1);

        // Gadmin.notifyMembers();

        member2.update(usb2);

        // Gadmin.notifyMembers();

    }

}
