/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.Settings;

import TerrainEditor.EditorWindow.EditorWindow_Controller;
import TerrainEditor.EditorWindow.EditorWindow_Model;
import TerrainEditor.EditorWindow.EditorWindow_View;
import TerrainEditor.TileEditor;
import com.jme3.app.SimpleApplication;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Jordan
 */
public class EditorWindowSettings_Controller implements java.awt.event.ActionListener
{
    private String commandChangeResolution = "ChangeResolution";
    private String commandLaunch = "Launch";
    private String commandIsWindowed = "isWindowed";
    EditorWindowSettings_Model settingsModel = new EditorWindowSettings_Model();
    EditorWindowSettings_View settingsView = new EditorWindowSettings_View();
    EditorWindow_Controller editorWindowController;
    SimpleApplication tileEditor;
    public EditorWindowSettings_Controller(EditorWindowSettings_Model settings_Model, EditorWindowSettings_View settings_View, SimpleApplication tileEditor)
    {
        this.settingsModel = settings_Model;
        this.settingsView = settings_View;
        this.tileEditor = tileEditor;
        settingsView.getSettingsJFrame().setVisible(true);
        settingsView.getResolutionComboBox().setModel(new DefaultComboBoxModel(settings_Model.getEditorWindowSizes()));
        settingsView.getResolutionComboBox().setSelectedIndex(settingsModel.getResolutionSelection());

        setupInputCommands();
        
    }
    private void setupInputCommands()
    {
        settingsView.getLaunchButton().setActionCommand(commandLaunch);
        settingsView.getLaunchButton().addActionListener(this);
        settingsView.getResolutionComboBox().setActionCommand(commandChangeResolution);
        settingsView.getResolutionComboBox().addActionListener(this);
        settingsView.getWindowedCheckBox().setActionCommand(commandIsWindowed);
        settingsView.getWindowedCheckBox().addActionListener(this);
    } 
    
    public EditorWindow_Controller getEditorWindowController()
    {
        return editorWindowController;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(commandIsWindowed.equals(e.getActionCommand()))
        {
            settingsModel.setWindowed(settingsView.getWindowedCheckBox().isSelected());
        }
        if(commandChangeResolution.equals(e.getActionCommand()))
        {
            settingsModel.setResolutionSelection(settingsView.getResolutionComboBox().getSelectedIndex());
            System.out.println(settingsModel.getHeight() + " x " + settingsModel.getWidth());
        }
        if(commandLaunch.equals(e.getActionCommand()))
        {
            EditorWindow_View view = new EditorWindow_View(tileEditor, settingsModel.getWidth(), settingsModel.getHeight(),settingsModel.isWindowed());
            EditorWindow_Model model = new EditorWindow_Model();
            editorWindowController = new EditorWindow_Controller(model,view,tileEditor);
            

            settingsView.getSettingsJFrame().setVisible(false);
            settingsView.getSettingsJFrame().dispose();
        }
    }
    
    
    
}
