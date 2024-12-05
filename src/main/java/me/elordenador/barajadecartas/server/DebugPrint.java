package me.elordenador.barajadecartas.server;

/**
 * DebugPrint is used to print depending of if you are on Debug-Mode or not
 * @version 1.0
 * @author Daniel Sánchez Úbeda
 */
public class DebugPrint {
    private static DebugPrint instance = null;
    
    private boolean debug;
    /**
     * Initializes the printer
     * @param debug if you want to enable Debug-Mode or not.
     */
    public DebugPrint(boolean debug) {
        this.debug = debug;
    }

    /**
     * Initialize debug printer
     * @param debug if you want to enable Debug-Mode or not.
     */
    public static void initialize(boolean debug) {
        instance = new DebugPrint(debug);
    }

    /**
     * Prints a text ignoring debug mode
     * @param text the text to print
     */
    public static void print(String text) {
        System.out.print("[");
        if (instance.debug) {
            System.out.print("DEBUG");
        } else {
            System.out.print("-");
        }
        System.out.print("] " + text);
    }

    /**
     * Prints a text ignoring debug mode, then adds a newline.
     * @param text the text to print
     */
    public static void println(String text) {
        System.out.print("[");
        if (instance.debug) {
            System.out.print("DEBUG");
        } else {
            System.out.print("-");
        }
        System.out.println("] " + text);
    }

    /**
     * Prints a text if debug mode is enabled
     * @param text the text to print
     */
    public static void dprint(String text) {
        if (instance.debug) {
            System.out.print("[DEBUG]" + text);
        } 
    }

    /**
     * Prints a text if debug mode is enabled, then adds a newline.
     * @param text the text to print
     */
    public static void dprintln(String text) {
        if (instance.debug) {
            System.out.print("[DEBUG]" + text);
        } 
    }



}
