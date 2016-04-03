/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author K
 */
public class DialogNewMap
{

    private JComboBox sizeCombo = null;
    private JComboBox tilesetCombo = null;
    private JDialog dialog;

    public int getSizeIndex()
    {
        return sizeCombo.getSelectedIndex();
    }

    public int getTilesetIndex()
    {
        return tilesetCombo.getSelectedIndex();
    }
    public String getTilesetName()
    {
        return tilesetCombo.getModel().getSelectedItem().toString();
    }
    public DialogNewMap(JFrame jFrame,SwingActionListener swingActionListener)
    {
        dialog = new JDialog(jFrame, "New Map");
        dialog.setResizable(false);
        dialog.setPreferredSize(new Dimension(225, 150));

        JPanel test = new JPanel(new GridBagLayout());

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

        JPanel topRow = new JPanel();
        topRow.setLayout(new GridBagLayout());
        GridBagConstraints constraintTopRow = new GridBagConstraints();

        JLabel tilesetLabel = new JLabel("Select Tileset: ");
        constraintTopRow.fill = GridBagConstraints.HORIZONTAL;
        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 0;
        topRow.add(tilesetLabel, constraintTopRow);

            File f = new File(System.getProperty("user.home") + "\\Planerrak\\Tilesets\\Data");
            
            
            File[] matchingFiles = f.listFiles();
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            
            for(int j=0; j < matchingFiles.length; j++)
            {
                model.addElement(matchingFiles[j].getName());
            }
        tilesetCombo = new JComboBox(model);
        constraintTopRow.gridx = 1;
        topRow.add(tilesetCombo, constraintTopRow);

        JLabel sizeLabel = new JLabel("Size: ");
        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 1;
        topRow.add(sizeLabel, constraintTopRow);

        sizeCombo = new JComboBox(new String[]
        {
            "8 x 8", "16 x 16", "32 x 32", "64 x 64", "128 x 128"
        });

        constraintTopRow.gridx = 1;
        topRow.add(sizeCombo, constraintTopRow);

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("CreateMap");
        okButton.addActionListener(swingActionListener);

        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 2;
        topRow.add(okButton, constraintTopRow);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setActionCommand("CancelMap");
        cancelButton.addActionListener(swingActionListener);
        constraintTopRow.gridx = 1;
        constraintTopRow.gridy = 2;
        topRow.add(cancelButton, constraintTopRow);


        dialogPanel.add(topRow);
        dialog.add(dialogPanel);
        //dialog.setUndecorated(true);
        dialog.pack();
        dialog.setVisible(false);
    }

    public void setVisible(boolean value)
    {
        dialog.setVisible(value);
    }
}
