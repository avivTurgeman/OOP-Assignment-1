package observer;

import java.util.Stack;

public class UndoableStringBuilder {
    /**
     * This class improves thr build in java <b><i>StringBuilder</i></b> class
     */

    private StringBuilder usb;
    private Stack<StringBuilder> stack;

    private int actions;

    /**
     * Empty constructor
     */
    public UndoableStringBuilder() {
        usb = new StringBuilder();
        stack = new Stack<>();
        actions = 0;
    }

    /**
     * The str will append as the UndoableStringBuilder's String value
     *
     * @param str a String
     */
    public UndoableStringBuilder(String str) {
        usb = new StringBuilder(str);
        stack = new Stack<>();
        actions = 0;
    }

    /**
     * Copy Constructor
     * the constructor will copy the field values of a given
     * UndoableStringBuilder to new addresses
     *
     * @param copy the UndoableStringBuilder to copy from
     */
    public UndoableStringBuilder(UndoableStringBuilder copy) {
        usb = new StringBuilder(copy.usb);
        stack = new Stack<>();
        stack.addAll(copy.stack);
        this.actions = copy.actions;
    }

    /**
     * Advanced Copy Constructor
     * the constructor will copy the field values of a given
     * UndoableStringBuilder to new addresses but <b><i>this actions</i></b> value will
     * be different according to the <b><i>actions</i></b> given parameter
     *
     * @param copy the UndoableStringBuilder to copy from
     * @param actions an int
     */
    public UndoableStringBuilder(UndoableStringBuilder copy, int actions) {
        usb = new StringBuilder(copy.usb);
        stack = new Stack<>();
        stack.addAll(copy.stack);
        this.actions = actions;
    }

    /**
     * Appends the specified string to this <b><i>StringBuilder</i></b> characters sequence.
     * The characters of the <b><i>String</i></b> argument are appended, in order,
     * increasing the length of this <b><i>StringBuilder</i></b> characters sequence by the length
     * of the argument.
     *
     * @param str a String
     * @return This Object
     */
    public UndoableStringBuilder append(String str) {
        StringBuilder temp = new StringBuilder(usb);
        stack.add(temp);
        usb.append(str);
        actions++;
        return this;
    }

    /**
     * Removes the characters in a substring of this StringBuilder characters
     * sequence. The substring begins at
     * the specified start and extends to the character at index end - 1 or to the
     * end of the
     * StringBuilder characters sequence if no such character exists. If start is
     * equal to end, no
     * changes are made.
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @return This Object
     */
    public UndoableStringBuilder delete(int start, int end) {
        try {
            StringBuilder temp = new StringBuilder(usb);
            stack.add(temp);
            usb.delete(start, end);
            actions++;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * The characters of the String argument are inserted, in order, into this
     * StringBuilder characters sequence at the indicated offset, moving up any
     * characters originally above
     * that position and increasing the length of this StringBuilder characters
     * sequence by the length of the
     * argument.
     *
     * @param offset the offset
     * @param str    a string
     * @return This Object
     */
    public UndoableStringBuilder insert(int offset, String str) {
        try {
            StringBuilder temp = new StringBuilder(usb);
            stack.add(temp);
            usb.insert(offset, str);
            actions++;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Replaces the characters in a substring of this StringBuilder characters
     * sequence
     * with characters in the specified String.
     * The substring begins at the specified start and extends to
     * the character at index end - 1 or to the end of the StringBuilder characters
     * sequence if no such
     * character exists. First the characters in the substring are removed and then
     * the specified String is inserted at start.
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @param str   String that will replace previous contents.
     * @return This Object.
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        try {
            StringBuilder temp = new StringBuilder(usb);
            stack.add(temp);
            usb.replace(start, end, str);
            actions++;
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return this;
    }


    /**
     * Causes this StringBuilder character sequence to be replaced by the reverse of
     * the sequence.
     * If there are any surrogate pairs included in the StringBuilder character
     * sequence,
     * these are treated as single characters for the reverse operation. Thus, the
     * order of the high-low
     * surrogates is never reversed. Let n be the character length of this
     * StringBuilder character
     * sequence (not the length in char values) just prior to execution of the
     * reverse method. Then the character at index k in the new StringBuilder
     * character sequence
     * is equal to the character at index n-k-1 in the old StringBuilder character
     * sequence.
     *
     * @return This Object
     */
    public UndoableStringBuilder reverse() {
        StringBuilder temp = new StringBuilder(usb);
        stack.add(temp);
        usb.reverse();
        actions++;
        return this;
    }

    /**
     * returning the UndoableStringBuilder to its former form.
     * if there is no former form, the UndoableStringBuilder will remain the same.
     */
    public void undo() {
        if ((!(stack.isEmpty())) && actions > 1) {
            usb = stack.pop();
            actions--;
        }
        else if(actions == 1){
            actions--;
            usb.replace(0,usb.toString().length(),"");
            stack.clear();
        }
    }


    public String toString() {
        return usb.toString();
    }
}