/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.Settings;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Jordan
 */
public class EditorWindowSettings_Model
{
    private boolean checkBox_Windowed;
    private String[] comboBox_EditorWindowResolution;
    private int[] resolutionHeight;
    private int[] resolutionWidth;
    private int resolutionSelection;
    private DisplayMode[] displayModes;
    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public EditorWindowSettings_Model()
    {
        /**
         * This simply removes duplicate sizes from the display so that the
         * user can choose which size they want the window to open by Default.
         * 
         * It takes the display sizes checks their width and height and if their different
         * adds them to a list of strings called comboBox_EditorWindowResolution.
         */
        displayModes = device.getDisplayModes();
        int stringCounter = 0;
        DisplayMode defaultResolution = device.getDisplayMode();
        int[] checkHeight = new int[displayModes.length];
        int[] checkWidth = new int[displayModes.length];
        boolean wasFound;
        for(int i=0; i < displayModes.length; i++)
        {
            wasFound = false;
            for(int j=0; j < displayModes.length; j++)
            {
                if(checkHeight[j] == displayModes[i].getHeight() && checkWidth[j] == displayModes[i].getWidth())
                {
                    
                    wasFound = true;
                }
            }
            if(wasFound == false)
            {
               if(defaultResolution.getHeight() == displayModes[i].getHeight() && defaultResolution.getWidth() == displayModes[i].getWidth())
               {
                   resolutionSelection = stringCounter;
               }
               checkHeight[stringCounter] = displayModes[i].getHeight();
               checkWidth[stringCounter] = displayModes[i].getWidth();
               stringCounter++;
            }
        }
        comboBox_EditorWindowResolution = new String[stringCounter];
        resolutionHeight = new int[stringCounter];
        resolutionWidth = new int[stringCounter];
        
        for(int i=0; i< stringCounter; i++)
        {
           resolutionHeight[i] = checkHeight[i];
           resolutionWidth[i] = checkWidth[i];
           comboBox_EditorWindowResolution[i] = checkHeight[i] + " x " + checkWidth[i];
        }
        
    }
    public int getHeight()
    {
        return resolutionHeight[resolutionSelection];
    }
    public int getWidth()
    {
        return resolutionWidth[resolutionSelection];
    }
    public int getResolutionSelection()
    {
        return resolutionSelection;
    }
    public void setResolutionSelection(int resolutionSelection)
    {
        this.resolutionSelection = resolutionSelection;
    }
    public String[] getEditorWindowSizes()
    {
        return comboBox_EditorWindowResolution;
    }
    public void setWindowed(boolean checkBox_Windowed)
    {
        this.checkBox_Windowed = checkBox_Windowed;
    }
    public boolean isWindowed()
    {
        return checkBox_Windowed;
    }
       
    
    
}
