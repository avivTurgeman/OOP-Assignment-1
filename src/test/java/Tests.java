import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){

        GroupAdmin groupAdmin = new GroupAdmin("groupAdmin");

        System.out.println(groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        groupAdmin.append("Before registering any members");
        System.out.println("******************  " + groupAdmin.getName() + " append: 'Before registering any members' ******************");
        System.out.println("******************  and sends an update to all of his members  ***************************\n.\n.");
        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());

        System.out.println("\n\n*********  " + groupAdmin.getName() + " register the 1st member  *********\n.\n.\n.");
        // Registering the 1st Member
        ConcreteMember member1 = new ConcreteMember(groupAdmin,"member1");

        System.out.println("\n\n(member1 UndoableStringBuilder will not be the same as " + groupAdmin.getName() + " members UndoableStringBuilder when register,");
        System.out.println(" once we use one of the UndoableStringBuilder methods on " + groupAdmin.getName() + ", member1 UndoableStringBuilder will update)");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("\n" + groupAdmin.getName() + " info:");

        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertNotEquals(groupAdmin.getUsb(), member1.getUsb());

        groupAdmin.replace(0,200,"1 members");

        System.out.println("\n***************  " + groupAdmin.getName() + " replace his UndoableStringBuilder String value to: '1 member' ********************");
        System.out.println("***************  and sends an update to all of his members  **************************************************\n.\n.");

        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertEquals(groupAdmin.getUsb(), member1.getUsb());

        System.out.println("\n*********  " + groupAdmin.getName() + " register the 2nd member  *********\n.\n.\n.");
        // Registering the 2nd Member
        ConcreteMember member2 = new ConcreteMember(groupAdmin,"member2");

        System.out.println("\n\n(member2 UndoableStringBuilder will not be the same as " + groupAdmin.getName() + " members UndoableStringBuilder when register,");
        System.out.println(" once we use one of the UndoableStringBuilder methods on " + groupAdmin.getName() + ", member2 UndoableStringBuilder will update)");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member2.getUsb())
        );

        System.out.println("\n**************  " + groupAdmin.getName() + " replace '1' to '2' ********************");
        System.out.println("**************  and sends an update to all of his members  *******\n.\n.");
        groupAdmin.replace(0,1,"2");

        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb())
        );

        System.out.println("\n*********  " + groupAdmin.getName() + " register the 3rd member  *********\n.\n.\n.");
        // Registering the 3rd Member
        ConcreteMember member3 = new ConcreteMember(groupAdmin,"member3");

        System.out.println("\n\n(member3 UndoableStringBuilder will not be the same as " + groupAdmin.getName() + " members UndoableStringBuilder when register,");
        System.out.println(" once we use one of the UndoableStringBuilder methods on " + groupAdmin.getName() + ", member3 UndoableStringBuilder will update)");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());
        System.out.println("   member3 UndoableStringBuilder String value: " + member3.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        System.out.println("\n****************  " + groupAdmin.getName() + " inserts '+1' after '2' **********************");
        System.out.println("****************  and sends an update to all of his members  *************\n.\n.");
        groupAdmin.insert(1, "+1");

        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());
        System.out.println("   member3 UndoableStringBuilder String value: " + member3.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        System.out.println("\n[" + groupAdmin.getName() + " members list before unregistering member3]");
        groupAdmin.printMembers();


        System.out.println("*********  " + groupAdmin.getName() + " unregister member3  *********\n.\n.\n.");
        groupAdmin.unregister(member3);

        System.out.println("\n[" + groupAdmin.getName() + " members list after unregistering member3]");
        groupAdmin.printMembers();

        System.out.println(groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        try {
            Thread.sleep(500); // sleep for 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n***************  " + groupAdmin.getName() + " undo his last action on his UndoableStringBuilder  ***************");
        System.out.println("***************  and sends an update to all of his members  **********************************");
        groupAdmin.undo();

        System.out.println("[member3 UndoableStringBuilder will remain the same]\n.\n.");

        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());
        System.out.println("   member3 UndoableStringBuilder String value: " + member3.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        System.out.println("\n*********** " + groupAdmin.getName() + " delete the String *******************");
        System.out.println("***********  and sends an update to all of his members  ****");
        groupAdmin.delete(0,19);

        System.out.println("[member3 UndoableStringBuilder will remain the same]\n.\n.");

        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());
        System.out.println("   member3 UndoableStringBuilder String value: " + member3.printUSB());

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb(), member1.getUsb()),
                () -> assertEquals(groupAdmin.getUsb(), member2.getUsb()),
                () -> assertNotEquals(groupAdmin.getUsb(), member3.getUsb())
        );

        System.out.println("***************************************************************** RETURNING member3 TO HIS 1st FORM  *****************************************************************\n\n");

        System.out.println("member3 UndoableStringBuilder String value before undo(): " + member3.printUSB());
        System.out.println("[member3.getUsb().undo()]");
        member3.getUsb().undo();
        System.out.println("member3 UndoableStringBuilder String value after undo() : " + member3.printUSB());

        System.out.println("\n****  When member3 UndoableStringBuilder is in his 1st form, he will remain the same after undoing again  ****\n");

        System.out.println("************* Creating member3 UndoableStringBuilder Copy to compare to (assertEquals) after undo().\n.\n.\n.");
        UndoableStringBuilder member3Copy = member3.getUsb();
        System.out.println("UndoableStringBuilder copy of member3 UndoableStringBuilder, created.\n");

        System.out.println("member3 UndoableStringBuilder String value before another undo(): " + member3.printUSB());
        System.out.println("[member3.getUsb().undo()]");
        member3.getUsb().undo();
        System.out.println("member3 UndoableStringBuilder String value after another undo() : " + member3.printUSB());

        System.out.println("\nComparing member3 UndoableStringBuilder before undo() to his UndoableStringBuilder after undo()");
        assertEquals(member3Copy,member3.getUsb());
        System.out.println(".\n.\n.\nTest passed, member3 UndoableStringBuilder wasn't change.\n");

        System.out.println("\n***************************************************************************************************************************************\n\n\n");

        System.out.println(groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        try {
            Thread.sleep(500); // sleep for 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nComparing member3 UndoableStringBuilder String value to " + groupAdmin.getName() + " UndoableStringBuilder String value:\n.\n.\n.");
        //checking correctness
        assertAll(
                () -> assertEquals(groupAdmin.getUsb().toString(), member1.getUsb().toString()),
                () -> assertEquals(groupAdmin.getUsb().toString(), member2.getUsb().toString()),
                () -> assertEquals(groupAdmin.getUsb().toString(), member3.getUsb().toString())
        );
        System.out.println("Test passed, member3 UndoableStringBuilder String value and " + groupAdmin.getName() + " UndoableStringBuilder String value are the same.\n\n");

        System.out.println("************* member3 UndoableStringBuilder String value compare to " + groupAdmin.getName() + " UndoableStringBuilder String value prints.\n.\n.");
        System.out.println(groupAdmin.getName() + " UndoableStringBuilder String value: " + groupAdmin.getUsb().toString());
        System.out.println("   member1 UndoableStringBuilder String value: " + member1.printUSB());
        System.out.println("   member2 UndoableStringBuilder String value: " + member2.printUSB());
        System.out.println("   member3 UndoableStringBuilder String value: " + member3.printUSB());

        System.out.println("\n\n***********************************************************************************************************************************************");
        System.out.println("* member3 UndoableStringBuilder String value and " + groupAdmin.getName() + " UndoableStringBuilder String value are equals since we undo them both             *");
        System.out.println("* separately to the same state but since member3 is not in the member list of groupAdmin, he have a different UndoableStringBuilder address   *");
        System.out.println("***********************************************************************************************************************************************\n");


        System.out.println("\nComparing member3 UndoableStringBuilder address to the  " + groupAdmin.getName() + "'s by identity HashCode:\n.\n.\n.");
        //checking correctness
        assertAll(
                () -> assertEquals(System.identityHashCode(groupAdmin.getUsb()), System.identityHashCode(member1.getUsb())),
                () -> assertEquals(System.identityHashCode(groupAdmin.getUsb()), System.identityHashCode(member2.getUsb())),
                () -> assertNotEquals(System.identityHashCode(groupAdmin.getUsb()), System.identityHashCode(member3.getUsb()))
        );
        System.out.println("Test passed, member3 UndoableStringBuilder Identity HashCode is different from " + groupAdmin.getName() + "'s.\n\n");

        System.out.println("member3 UndoableStringBuilder Identity HashCode compare to " + groupAdmin.getName() + "'s UndoableStringBuilder Identity HashCode prints.\n.\n.");
        System.out.println(groupAdmin.getName() + " UndoableStringBuilder identity HashCode: " + System.identityHashCode(groupAdmin.getUsb()));
        System.out.println("   member1 UndoableStringBuilder identity HashCode: " + System.identityHashCode(member1.getUsb()));
        System.out.println("   member2 UndoableStringBuilder identity HashCode: " + System.identityHashCode(member2.getUsb()));
        System.out.println("   member3 UndoableStringBuilder identity HashCode: " + System.identityHashCode(member3.getUsb()) + " != " + System.identityHashCode(groupAdmin.getUsb()));

        System.out.println("\n" + groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        try {
            Thread.sleep(500); // sleep for 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n********** Tests for registering a registered member and unregistering an unregistered member *****************\n");

        groupAdmin.printMembers();

        System.out.println(groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        try {
            Thread.sleep(500); // sleep for 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("********** Trying to register member1 again:");
        System.out.println(".\n.\n.\n");
        groupAdmin.register(member1);

        System.out.println();
        groupAdmin.printMembers();

        System.out.println(groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));

        //checking correctness
        assertNotEquals(groupAdmin.getMembers().get(groupAdmin.getMembers().size() - 1), member1);

        try {
            Thread.sleep(500); // sleep for 0.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("******** Trying to unregister member3 again:\n.\n.\n.");

        groupAdmin.unregister(member3);

        System.out.println();
        groupAdmin.printMembers();

        System.out.println(groupAdmin.getName() + " info:");
        logger.info(()->JvmUtilities.memoryStats(groupAdmin));
    }
}
