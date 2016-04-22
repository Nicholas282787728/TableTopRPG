/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.MenuBar;




import TerrainEditor.EditorWindow.EditorWindow_Controller;
import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_Controller;
import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_Model;
import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_View;
import TerrainEditor.EditorWindow.NewTilesetDialog.DialogNewTileset_Controller;
import TerrainEditor.EditorWindow.NewTilesetDialog.DialogNewTileset_Model;
import TerrainEditor.EditorWindow.NewTilesetDialog.DialogNewTileset_View;
import TerrainEditor.JavaFX.DialogNewTileset;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.system.JmeCanvasContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class MenuBar_Controller implements ActionListener
{

    private MenuBar_Model menuBar_Model;
    private MenuBar_View menuBar_View;
    private DialogNewMap_Controller dialogNewMap_Controller;
    private EditorWindow_Controller editorWindow_Controller;

    private String commandNew_Map = "New_Map";
    private String commandSave_Map = "Save_Map";
    private String commanLoad_Map = "Load_Map";
    private String commandFinalize_Map = "Finalize_Map";
    private String commandExit_Editor = "Exit";
    private String commandNew_Tileset = "New_Tileset";
    private String commandView_Tileset = "View_Tileset";

    public MenuBar_Controller(MenuBar_Model menuBar_Model, MenuBar_View menuBar_View, EditorWindow_Controller editorWindow_Controller)
    {
        this.editorWindow_Controller = editorWindow_Controller;
        this.menuBar_Model = menuBar_Model;
        this.menuBar_View = menuBar_View;
        
        setupInputCommands();
    }
    private void setupInputCommands()
    {
        menuBar_View.getFileNewMap().setActionCommand(commandNew_Map);
        menuBar_View.getFileSaveMap().setActionCommand(commandSave_Map);
        menuBar_View.getFileLoadMap().setActionCommand(commanLoad_Map);
        menuBar_View.getFileFinalizeMap().setActionCommand(commandFinalize_Map);
        menuBar_View.getExitEditor().setActionCommand(commandExit_Editor);
        menuBar_View.getNewTileset().setActionCommand(commandNew_Tileset);
        menuBar_View.getViewTileset().setActionCommand(commandView_Tileset);

        menuBar_View.getFileNewMap().addActionListener(this);
        menuBar_View.getFileSaveMap().addActionListener(this);
        menuBar_View.getFileLoadMap().addActionListener(this);
        menuBar_View.getFileFinalizeMap().addActionListener(this);
        menuBar_View.getExitEditor().addActionListener(this);
        menuBar_View.getNewTileset().addActionListener(this);
        menuBar_View.getViewTileset().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();


        if (commandNew_Map.equals(actionCommand))
        {
            DialogNewMap_View dialogNewMap_View = new DialogNewMap_View(editorWindow_Controller.getView().getEditorWindow());
            DialogNewMap_Model dialogNewMap_Model = new DialogNewMap_Model();
            dialogNewMap_Controller = new DialogNewMap_Controller(dialogNewMap_Model,dialogNewMap_View, editorWindow_Controller);
            //JDialog newMap = editorWindowModel.getEditorWindowJDialog_New();
            //newMap.setVisible(true);

        }
        if (commandSave_Map.equals(actionCommand))
        {
        }
        if (commanLoad_Map.equals(actionCommand))
        {
        }
        if (commandFinalize_Map.equals(actionCommand))
        {
        }
        if (commandExit_Editor.equals(actionCommand))
        {
            editorWindow_Controller.getView().getCanvas().destroy(true);
            //editorWindow_Controller.getView().getEditorWindow().setVisible(false);
            //editorWindow_Controller.getView().getEditorWindow().dispose();
            //Call the close function like clicking the X in the top right corner
            editorWindow_Controller.getView().getEditorWindow().dispatchEvent(new WindowEvent(editorWindow_Controller.getView().getEditorWindow(), WindowEvent.WINDOW_CLOSING));
            editorWindow_Controller.getApplication().getContext().destroy(false);
            editorWindow_Controller.getApplication().stop();
            
        }
        if (commandNew_Tileset.equals(actionCommand))
        {

            DialogNewTileset_Controller controller;
            DialogNewTileset_View view = new DialogNewTileset_View(editorWindow_Controller.getView().getEditorWindow());
            DialogNewTileset_Model model = new DialogNewTileset_Model(editorWindow_Controller.getApplication().getAssetManager());
            
            controller = new DialogNewTileset_Controller(model, view);
            
            
            //DialogNewTileset dial = new DialogNewTileset(editorWindow_Controller.getView().getEditorWindow());
            if (DEV_MODE)
            {
                //dial.initialize(true, editorWindow_Controller.getApplication().getAssetManager(), "\\Tilesets\\", System.getProperty("user.dir") + "\\assets\\Tilesets");
            }
            else
            {
                //dial.initialize(true, editorWindow_Controller.getApplication().getAssetManager(), "\\Tilesets\\", System.getProperty("user.home") + "\\Planerrak\\Tilesets");

            }
        }
        if (commandView_Tileset.equals(actionCommand))
        {
            // DialogViewTileset test = new DialogViewTileset(editorWindow);


            System.out.print("TT");
        }


        if (e.getActionCommand().startsWith("TileButton"))
        {
            String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = test.split("_");
            System.out.println("Frame" + temp[1] + "Row" + temp[0]);
            // selectedFrame = Integer.parseInt(temp[1]);
            //selectedRow = Integer.parseInt(temp[0]);
            //leftPanel.updateCurrentTileIcon(selectedRow,selectedFrame);


        }
        if ("CreateMap".equals(actionCommand))
        {
           //editorWindowModel.createMap();

        }
        if ("CancelMap".equals(actionCommand))
        {
            //editorWindowModel.getEditorWindowJDialog_New().setVisible(false);
        }
        if ("Select_Tileset_Size".equals(actionCommand))
        {
           // editorWindowModel.setTilesetSize();
        }
    }
}
