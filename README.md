# OOP-Assignment-1
OOP Assignment 1

This repository contains a project that implements the Observer Design Pattern.

We added to the given Assignment folder, the following classes to src/main/java/observer:
  1. UndoableStringBuilder.java
  2. GroupAdmin.java 
  3. ConcreteMember.java 

And we modified the 'Tests.java' class from src/test/java/Tests.java

ADDED CLASSES DESCRIPTION:

  1. UndoableStringBuilder.java:

    this class is an upgrade to the UndoableStringBuilder class from our last OOP Assignment.
    we added a new feild variable (private int actions) that indicates for each UndoableStringBuilder,
    how many times he's been modified. 
    the methods append(), replace(), insert(), delete() and reverse() will increase the value of 'actions' by 1
    and the method undo() will decrease the value of 'actions' by 1.

    on top of that we added a new Advenced Copy Constructor UndoableStringBuilder(UndoableStringBuilder copy, int actions) 
    that copies all of the UndoableStringBuilder (that was given as a parameter), field values except of the actions
    so this.actions will be equal to the actions int that was given as a parameter.
  
  2. GroupAdmin.java:

    this class will implemet the 'Sender.java' interface and will represent the Observable side of the Observer Design Pattern.
    
    this class has 3 field variables:
    
    a) members ArrayList - this ArtayList will contain the members of the GroupAdmin.
    b) actionsPerMember ArrayList - each value in a specific index in this ArrayList will represent the 
       current amount of actions that the corresponding members[index] member has made.
    c) String name - the name of this GroupAdmin.
  
    in addition to the methods of Sender.java that we implemented, we added the following methods:
    
    a) void replace(int start, int end, String str) - operates the replace method from UndoableStringBuilder.java
       on this GroupAdmin's UndoableStringBuilder and updates this GroupAdmin members about it.
    b) getUSB() - returns this GroupAdmin's UndoableStringBuilder for our use.
    c) getMembers() - return this GroupAdmin's members ArrayList for our use.
    d) getName() - return this GroupAdmin's name for our use.
    e) printMembers() - this method prints all of the members in this GroupAdmin one by one.
    f) notifyMembers() - this method sends an update to every member of this GroupAdmin.
    
  3. ConcreteMember.java:
  
    this class will imlement the 'Member.java' interface and will represent the Observer side of the Observer Design Pattern.
    
    this class has 3 field variables:   
    
      a) Sender groupAdmin - a Sender that sends updates to this ConcreteMember.
      b) UndoableStringBuilder usb - an UndoableStringBuilder that can be modified by this Sender.
      c) String name - the name of this ConcreteMember.

    we implement the update(UndoableStringBuilder usb) method of the 'Member.java' interface by assigning
    this ConcreteMember UndoableStringBuilder to point to the same address (Shallow copy) 
    as the UndoableStringBuilder we received as a parameter.
    
    in addition, we added 3 methods to this class: 
    
      a) String printUSB() - a method which returns this ConcreteMember's UndoableStringBuilder String value.
      b) UndoableStringBuilder getUsb() - a method which returns this ConcreteMember's UndoableStringBuilder for our use.
      c) String toString() - a method which returns the name of this ConcreteMember.


THE MODIFYING AND USE OF Tests.java: 

    This is a JUnit test class that tests the functionality of the observer package.
    The observer package contains classes that implement the Observer design pattern.

    The use of the JvmUtilities.java class for the tests:

      The JvmUtilities class contains static utility methods for printing JVM memory stats.
      The method used in this test class is memoryStats(Object object),
      which prints the memory usage of the given object and the JVM as a whole.

    The test class has a single test method test() which tests the following functionality:

      1) Creation of a GroupAdmin object with the name groupAdmin.

      2) Printing groupAdmin's name and JVM memory stats using the JvmUtilities class.

      3) Appending the string "Before registering any members" to groupAdmin's UndoableStringBuilder
         and printing the resulting string.

      4) Registering the first ConcreteMember object with groupAdmin and printing the resulting
         UndoableStringBuilder strings for both groupAdmin and the ConcreteMember.

      5) Replacing a substring in groupAdmin's UndoableStringBuilder with the string "1 member"
         and printing the resulting strings for both groupAdmin and the ConcreteMember.

      6) Registering a second ConcreteMember object with groupAdmin and printing the resulting
         UndoableStringBuilder strings for both groupAdmin and the new ConcreteMember.

      7) Undoing the last action on groupAdmin's UndoableStringBuilder and printing the resulting strings
         for both groupAdmin and the new ConcreteMember.

      8) Registering a third ConcreteMember object with groupAdmin and printing the resulting
         UndoableStringBuilder strings for both groupAdmin and the new ConcreteMember.

      9) Undoing the last action on the third ConcreteMember's UndoableStringBuilder and printing
         the resulting string for the ConcreteMember.

      10) Undoing the last action again on the third ConcreteMember's UndoableStringBuilder
         and printing the resulting string for the ConcreteMember. 
         The test then compares the UndoableStringBuilder before and after the second undo operation
         using assertEquals() to ensure that the UndoableStringBuilder has not changed.

      11) The test creates a copy of the third ConcreteMember's UndoableStringBuilder and compares it
         to the UndoableStringBuilder after the second undo operation using assertEquals() 
         to ensure that the UndoableStringBuilder has not changed.

      12) The test uses assertAll() to compare the UndoableStringBuilder strings of all three ConcreteMember objects
         to groupAdmin's UndoableStringBuilder string using assertEquals().

      13) The test unregisters the third ConcreteMember from groupAdmin and compares the UndoableStringBuilder strings
         of the remaining ConcreteMember objects to groupAdmin's UndoableStringBuilder string using assertEquals().

      14) The test undoes the last action on groupAdmin's UndoableStringBuilder and compares the resulting strings
         for groupAdmin and the remaining ConcreteMember objects using assertEquals().

      15) The test performs a series of actions on groupAdmin's UndoableStringBuilder (appending, replacing, and undoing)
         and compares the resulting strings for groupAdmin and the remaining ConcreteMember objects using assertEquals()
         after each action.

      16) The test prints the memory stats for groupAdmin using the JvmUtilities class.

      17) The test sleeps for 0.5 seconds and then compares the UndoableStringBuilder strings
         of all three ConcreteMember objects to groupAdmin's UndoableStringBuilder string
         using assertAll() and assertEquals().

      18) The test performs a series of actions on groupAdmin's UndoableStringBuilder (appending, replacing, and undoing)
         and compares the resulting strings for groupAdmin and the remaining ConcreteMember objects using assertEquals()
         after each action.

      19) The test prints the memory stats for groupAdmin using the JvmUtilities class.

      20) The test unregisters the remaining ConcreteMember objects from groupAdmin and compares
         the UndoableStringBuilder strings of the ConcreteMember objects to groupAdmin's UndoableStringBuilder string
         using assertEquals().

      21) The test prints the memory stats for groupAdmin using the JvmUtilities class.
    
    
  RUNNING THE TESTS:
  
    1. To run the tests, you will need to have JUnit 5 and a Java development environment set up. 
    
    2. Clone this repository to your chosen location.
    
    3. Open the Tests.java file in your preferred
       Java development environment (e.g. Eclipse, IntelliJ IDEA, etc.).
     
    4. Use your development environment's built-in support for running JUnit tests
       to run the test() method in the Tests class. This is usually done by right-clicking on the test method
       and selecting "Run As" > "JUnit Test" (in Eclipse) 
       or by using a keyboard shortcut (e.g. Ctrl+Shift+F10 in IntelliJ IDEA).
    

    
    
    
    
    
    
    
    
    
    
