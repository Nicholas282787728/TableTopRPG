/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewTilesetDialog;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.EditorWindow.Panels.PanelTextureButton;
import com.jme3.texture.Texture;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import jme3tools.converters.ImageToAwt;

/**
 *
 * @author Jordan
 */
public class PanelNewTilesetSelection_View
{

    private JDialog dialogSelectDefault;
    /**
     * Tileset Default Selection Variables
     *
     */
    private JLabel jLabelCurrent;
    private JLabel jLabelIcon;
    private JPanel jPanelButtons;
    private JScrollPane jScrollPaneSelection;
    JButton jButtonOKView;
    JButton jButtonCancelView;
    PanelTextureButton jPanelTextureButton;
    //private int pixelSize;
    private GridBagConstraints constraint;

    public PanelNewTilesetSelection_View(JDialog dialogNewTileset, int width, int height, Texture texture, ActionListener actionListener, String actionCommand, int pixels, int frame, int rows)
    {
        dialogSelectDefault = new JDialog(dialogNewTileset, "Select Default Tile");
        dialogSelectDefault.setResizable(true);
        dialogSelectDefault.setMinimumSize(new Dimension(350, 350));
        dialogSelectDefault.setPreferredSize(new Dimension(width / 2, height / 2));




        GridBagConstraints constraintsDefault;


        jPanelButtons = new JPanel();
        jButtonOKView = new JButton();
        jButtonCancelView = new JButton();
        jLabelCurrent = new JLabel();
        jLabelIcon = new JLabel();

        dialogSelectDefault.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //dialogSelectDefault.setPreferredSize(new Dimension(500, 500));
        dialogSelectDefault.getContentPane().setLayout(new GridBagLayout());
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 0;
        constraintsDefault.gridy = 0;
        constraintsDefault.gridwidth = 3;
        constraintsDefault.gridheight = 3;
        constraintsDefault.fill = GridBagConstraints.BOTH;
        constraintsDefault.weightx = 0.75;
        constraintsDefault.weighty = 0.75;
        constraintsDefault.insets = new Insets(10, 10, 10, 10);
        jPanelTextureButton = new PanelTextureButton();
        JPanel scrollPanel = new JPanel();
        jScrollPaneSelection = new JScrollPane(scrollPanel);

        

        //dialogSelectDefault.getContentPane().add(jPanelTextureButton.createDefaultSelectionPanel(texture, actionListener,actionCommand,pixels, frame, rows), constraintsDefault);
        dialogSelectDefault.getContentPane().add(jScrollPaneSelection, constraintsDefault);


        jPanelButtons.setPreferredSize(new Dimension(100, 50));
        jPanelButtons.setLayout(new GridBagLayout());

        jButtonOKView.setText("OK");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 0;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 10, 0, 10);
        jPanelButtons.add(jButtonOKView, constraintsDefault);


        jLabelCurrent.setText("Current:");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 2;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 10, 0, 0);
        jPanelButtons.add(jLabelCurrent, constraintsDefault);

        jLabelIcon.setText("");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 3;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 5, 0, 0);
        jPanelButtons.add(jLabelIcon, constraintsDefault);

        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 0;
        constraintsDefault.gridy = 4;
        constraintsDefault.gridwidth = 3;
        constraintsDefault.fill = GridBagConstraints.HORIZONTAL;
        constraintsDefault.weightx = 0.25;
        constraintsDefault.insets = new Insets(0, 10, 10, 10);
        dialogSelectDefault.getContentPane().add(jPanelButtons, constraintsDefault);



        scrollPanel.add(jPanelTextureButton.createDefaultSelectionPanel(texture, actionListener, actionCommand, pixels, frame, rows));
        jLabelIcon.setIcon(jPanelTextureButton.getImage(0, 0));

        constraint = new GridBagConstraints();

        dialogSelectDefault.setVisible(true);
        dialogSelectDefault.pack();
    }

    public JLabel getCurrentIcon()
    {
        return jLabelIcon;
    }

    public ImageIcon getTextureButtonPanel(int row, int frame)
    {
        return jPanelTextureButton.getImage(row, frame);
    }

    public JButton getjButtonOKView()
    {
        return jButtonOKView;
    }
    public JDialog getDialog()
    {
        return dialogSelectDefault;
    }
}

