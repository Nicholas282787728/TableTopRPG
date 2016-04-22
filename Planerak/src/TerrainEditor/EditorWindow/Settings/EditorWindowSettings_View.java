/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.Settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Jordan
 */
public class EditorWindowSettings_View
{

    
    JFrame jFrameSettingsWindow;
    JPanel jPaneSettingsWindow;
    JTabbedPane jTabbedPaneSettingWindow;
    JPanel SettingsTab;
    JPanel jPaneSettingsLeft;
    JPanel jPanelSettingsRight;
    JCheckBox jCheckBoxWindowed;
    JButton jButtonLaunch;
    JComboBox jComboBoxResolution;
    JPanel UserPathTab;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JLabel jLabel5;
    JTextField jTextField1;
    JTextField jTextField2;
    JTextField jTextField3;
    JTextField jTextField4;
    JTextField jTextField6;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;
    JButton jButton5;
    JButton jButton6;

    public EditorWindowSettings_View()
    {
        
        
        this.jFrameSettingsWindow = new JFrame();
        this.jButton6 = new JButton();
        this.jButton5 = new JButton();
        this.jButton4 = new JButton();
        this.jButton3 = new JButton();
        this.jButton2 = new JButton();
        this.jTextField6 = new JTextField();
        this.jTextField4 = new JTextField();
        this.jTextField3 = new JTextField();
        this.jTextField2 = new JTextField();
        this.jTextField1 = new JTextField();
        this.jLabel5 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel1 = new JLabel();
        this.UserPathTab = new JPanel();
        this.jComboBoxResolution = new JComboBox();
        this.jButtonLaunch = new JButton();
        this.jPanelSettingsRight = new JPanel();
        this.jCheckBoxWindowed = new JCheckBox();
        this.jPaneSettingsLeft = new JPanel();
        this.SettingsTab = new JPanel();
        this.jPaneSettingsWindow = new JPanel();
        this.jTabbedPaneSettingWindow = new JTabbedPane();
        GridBagConstraints gridBagConstraints;
        jPaneSettingsWindow.setLayout(new BoxLayout(jPaneSettingsWindow, BoxLayout.LINE_AXIS));

        jTabbedPaneSettingWindow.setTabPlacement(JTabbedPane.LEFT);
        jTabbedPaneSettingWindow.setName(""); // NOI18N
        jTabbedPaneSettingWindow.setPreferredSize(new Dimension(400, 300));

        SettingsTab.setLayout(new GridBagLayout());

        jPaneSettingsLeft.setBackground(new Color(212, 255, 200));
        jPaneSettingsLeft.setLayout(new GridBagLayout());

        jCheckBoxWindowed.setText("Windowed");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPaneSettingsLeft.add(jCheckBoxWindowed, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        SettingsTab.add(jPaneSettingsLeft, gridBagConstraints);

        jPanelSettingsRight.setBackground(new Color(255, 208, 200));
        jPanelSettingsRight.setLayout(new GridBagLayout());

        jButtonLaunch.setText("Start");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.2;
        jPanelSettingsRight.add(jButtonLaunch, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.2;
        jPanelSettingsRight.add(jComboBoxResolution, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        SettingsTab.add(jPanelSettingsRight, gridBagConstraints);

        jTabbedPaneSettingWindow.addTab("Settings", SettingsTab);

        UserPathTab.setLayout(new GridBagLayout());

        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setText("Tilesets:");
        jLabel1.setToolTipText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(SwingConstants.TRAILING);
        jLabel2.setText("Sprites:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Textures:");
        jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Maps:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Worlds:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jLabel5, gridBagConstraints);

        jTextField1.setText("jTextField1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jTextField1, gridBagConstraints);

        jTextField2.setText("jTextField2");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jTextField2, gridBagConstraints);

        jTextField3.setText("jTextField3");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jTextField3, gridBagConstraints);

        jTextField4.setText("jTextField4");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jTextField4, gridBagConstraints);

        jTextField6.setText("jTextField6");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jTextField6, gridBagConstraints);

        jButton2.setText("jButton2");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jButton2, gridBagConstraints);

        jButton3.setText("jButton3");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jButton3, gridBagConstraints);

        jButton4.setText("jButton4");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jButton4, gridBagConstraints);

        jButton5.setText("jButton5");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jButton5, gridBagConstraints);

        jButton6.setText("jButton6");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        UserPathTab.add(jButton6, gridBagConstraints);

        jTabbedPaneSettingWindow.addTab("User Paths", null, UserPathTab, "");

        jPaneSettingsWindow.add(jTabbedPaneSettingWindow);
        jTabbedPaneSettingWindow.getAccessibleContext().setAccessibleName("JPanel");

        jFrameSettingsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameSettingsWindow.add(jPaneSettingsWindow, BorderLayout.CENTER);

        jFrameSettingsWindow.pack();
        
        
    }
    public JFrame getSettingsJFrame()
    {
        return jFrameSettingsWindow;
    }
    public JButton getLaunchButton()
    {
        return jButtonLaunch;
    }
    public JComboBox getResolutionComboBox()
    {
        return jComboBoxResolution;
    }
    public JCheckBox getWindowedCheckBox()
    {
        return jCheckBoxWindowed;
    }
    
    
}
