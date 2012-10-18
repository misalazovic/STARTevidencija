package logic;

import javax.swing.JFileChooser;

/**
 *
 * @author Misa
 */
public class Logic {

    public static String selectPath() {
        JFileChooser fc = new JFileChooser();
        fc.setVisible(true);
        int returnVal = fc.showOpenDialog(null);
        fc.setMultiSelectionEnabled(false);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().toString();
        }
        return null;
    }
}
