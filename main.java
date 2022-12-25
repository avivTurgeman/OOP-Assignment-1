package observer;

public class main {
    public static void main(String[] args) {

        GroupAdmin Gadmin1 = new GroupAdmin("Gadmin1");

        ConcreteMember member1 = new ConcreteMember(Gadmin1, "member 1");
        ConcreteMember member2 = new ConcreteMember(Gadmin1, "member 2");

        String usb2 = "usb2";

        Gadmin1.append("usb1");

        System.out.println("\n**************  append('usb1')  ******************");

        member1.printUSB();
        member2.printUSB();

        System.out.println("\n***************  replace to 'usb2' *****************");

        Gadmin1.replace(0,9,usb2);

        member1.printUSB();
        member2.printUSB();

        System.out.println("\n***************  undo()   ******************");

        Gadmin1.undo();

        member1.printUSB();
        member2.printUSB();

        System.out.println("\n***************  usb identity hash code   ******************");

        member1.print_USB_IdentityHashCode();
        member2.print_USB_IdentityHashCode();

        System.out.println("\n****************  members identity hash code  *****************");

        System.out.println("member 1 identity hash code: " + System.identityHashCode(member1));
        System.out.println("member 2 identity hash code: " + System.identityHashCode(member2));

    }

}