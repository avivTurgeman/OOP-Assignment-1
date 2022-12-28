import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){

        GroupAdmin groupAdmin = new GroupAdmin("groupAdmin");

        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        groupAdmin.append("Before registering any members");
        System.out.println("**********  Group Admin append: 'Before registering any members' *************");

        // Registering the first member who has a different UndoableStringBuilder Object from the Group Admin
        ConcreteMember member1 = new ConcreteMember(groupAdmin,"member1");

        System.out.println("****************  Group Admin register the 1st member **********************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertNotEquals(groupAdmin.getUsb(), member1.getUsb());

        groupAdmin.replace(0,200,"1 members");

        System.out.println("\n***************  Group Admin replace all to: '1 member' ********************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertEquals(groupAdmin.getUsb(), member1.getUsb());

        ConcreteMember member2 = new ConcreteMember(groupAdmin,"member2");

        System.out.println("\n****************  Group Admin register the 2nd member **********************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member2.getUsb())
        );

        groupAdmin.replace(0,1,"2");

        System.out.println("\n**************  Group Admin replace '1' to '2' ********************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb())
        );

        ConcreteMember member3 = new ConcreteMember(groupAdmin,"member3");
        System.out.println("\n**************  Group Admin register the 3rd member *******************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("member 3 usb String value: " + member3.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        groupAdmin.insert(1, "+1");

        System.out.println("\n****************  Group Admin, insert method **********************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("member 3 usb String value: " + member3.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        groupAdmin.unregister(member3);
        System.out.println("\n**************  Group Admin unregister the 3rd member *******************");
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        try {
            Thread.sleep(1000); // sleep for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        groupAdmin.undo();
        System.out.println("\n*********************  Group Admin, undo method *************************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("member 3 usb String value: " + member3.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        groupAdmin.delete(0,19);
        System.out.println("\n******************* Group Admin delete the String *************************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("member 3 usb String value: " + member3.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        System.out.println("member 3 usb String value: " + member3.printUSB());
        System.out.println("**************** Undoing member 3 to his 1st position **********************");

        member3.getUsb().undo();
        System.out.println("member 3 usb String value: " + member3.printUSB());

        System.out.println("\n*********** Member 3 remains in his 1st position after undoing again *****************");

        member3.getUsb().undo();
        System.out.println("member 3 usb String value: " + member3.printUSB());

        System.out.println("\n**************** Comparing member 3 usb String value to GroupAdmin's  **********************");
        System.out.println("Group Admin usb String value: " + groupAdmin.getUsb().toString());
        System.out.println("member 1 usb String value: " + member1.printUSB());
        System.out.println("member 2 usb String value: " + member2.printUSB());
        System.out.println("member 3 usb String value: " + member3.printUSB());
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        try {
            Thread.sleep(1000); // sleep for 1 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n********** Comparing member 3 usb address to GroupAdmin's by identity HashCode ****************");
        System.out.println("Group Admin usb identity HashCode: " + System.identityHashCode(groupAdmin.getUsb()));
        System.out.println("member 1 usb identity HashCode: " + System.identityHashCode(member1.getUsb()));
        System.out.println("member 2 usb identity HashCode: " + System.identityHashCode(member2.getUsb()));
        System.out.println("member 3 usb identity HashCode: " + System.identityHashCode(member3.getUsb()));
        System.out.println("Group Admin info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(System.identityHashCode(groupAdmin.getUsb()), System.identityHashCode(member1.getUsb())),
                () -> assertEquals(System.identityHashCode(groupAdmin.getUsb()), System.identityHashCode(member2.getUsb())),
                () -> assertNotEquals(System.identityHashCode(groupAdmin.getUsb()), System.identityHashCode(member3.getUsb()))
        );

    }
}
