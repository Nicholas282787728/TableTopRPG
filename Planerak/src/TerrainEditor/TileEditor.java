/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor;



import TerrainEditor.CustomFiles.TilesetLoader;
import TerrainEditor.EditorWindow.EditorWindowView;
import TerrainEditor.EditorWindow.EditorWindow_Controller;
import TerrainEditor.EditorWindow.EditorWindow_Model;
import TerrainEditor.EditorWindow.EditorWindow_View;
import TerrainEditor.EditorWindow.Settings.EditorWindowSettings_Controller;
import TerrainEditor.EditorWindow.Settings.EditorWindowSettings_Model;
import TerrainEditor.EditorWindow.Settings.EditorWindowSettings_View;
import TerrainEditor.JavaFX.SwingActionListener;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;

/**
 *
 * @author K
 */
public class TileEditor extends SimpleApplication
{
    
    private static TileEditor tileEditor;
    TerrainPanel panel;
    static EditorWindowView frameEditor;
    private static DisplayMode currentMode;
    static SwingActionListener swingActionListener;
    public static final boolean DEV_MODE = true;
    private static EditorWindowSettings_Controller settingControl;
    @Override
    public void simpleInitApp()
    {
        
        settingControl.getEditorWindowController().setupInputCanvasCommands();
        /*
        //Initialize some basic inputs
        inputManager.addMapping("Select_Tile", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Select_Tile");
        
        inputManager.addMapping("Move_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addListener(analogListener, "Move_Up");
        
        inputManager.addMapping("Move_Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener(analogListener, "Move_Down");
        
        inputManager.addMapping("Move_Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addListener(analogListener, "Move_Left");
        
        inputManager.addMapping("Move_Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addListener(analogListener, "Move_Right");
        //allows you to move the mouse around and shows it
        */
        /*
        inputManager.addMapping("Select_Tile", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(swingActionListener, "Select_Tile");
        tileEditor.getInputManager();

        
        inputManager.addMapping("Move_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addListener(swingActionListener, "Move_Up");
        
        inputManager.addMapping("Move_Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener(swingActionListener, "Move_Down");
        
        inputManager.addMapping("Move_Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addListener(swingActionListener, "Move_Left");
        
        inputManager.addMapping("Move_Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addListener(swingActionListener, "Move_Right");
        */
        

        //Create Default Directories
        
        String userHome = System.getProperty("user.home") + "\\Planerrak";
        //Typically on Windows C:\Users\<NAME>\Planerrak\
        String tilesets = userHome + "\\Tileset\\Saved";
        String tilesetTextures = userHome + "\\Tileset\\Textures";
        String sprites = userHome + "\\Sprites";
        String tilesetsAssets = System.getProperty("user.dir")+ "\\assets\\Tilesets";
        String tilesetsAssetsTerrain = System.getProperty("user.dir")+ "\\assets\\Textures\\Terrain";
        
        Boolean homeFolder = new File(userHome).mkdir();
        Boolean tilesetFolder = new File(tilesets).mkdirs();
        Boolean tilesetTextureFolder = new File(tilesetTextures).mkdirs();
        Boolean spriteFolder = new File(sprites).mkdir();
        

        System.out.println(userHome);
                //AssetManager ass = application.getAssetManager();
        
        tileEditor.getAssetManager().registerLocator(userHome, FileLocator.class);
        tileEditor.getAssetManager().registerLocator(tilesetsAssets, FileLocator.class);
        tileEditor.getAssetManager().registerLocator(tilesetsAssetsTerrain, FileLocator.class);
        tileEditor.getAssetManager().registerLocator(tilesetTextures, FileLocator.class);
        tileEditor.getAssetManager().registerLocator(tilesets, FileLocator.class);
        tileEditor.getAssetManager().registerLocator(sprites, FileLocator.class);
        tileEditor.getAssetManager().registerLoader(TilesetLoader.class, "TileSet");
        //MainPanel panel = new MainPanel(tileEditor,currentMode.getWidth(),currentMode.getHeight());
        //panel = new TerrainPanel(32, 32,32,32, "Panel",assetManager,"Textures/LPC Terrain/terrain_atlas.png");
        //guiNode.attachChild(panel.getNode());
        //frameEditor.setVisible(true);
        //swingActionListener.setApplication(tileEditor);
    }
    
    public static void main(String[] args)
    {
        tileEditor = new TileEditor();
        tileEditor.setDisplayFps(true);
        //displays stats like objects
        tileEditor.setDisplayStatView(true);
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        currentMode = device.getDisplayMode();

        AppSettings settings = new AppSettings(true);
        settings.setWidth(currentMode.getWidth());
        settings.setHeight(currentMode.getHeight());

        settings.setFrameRate(60);

        tileEditor.setSettings(settings);
        tileEditor.setPauseOnLostFocus(false);
        tileEditor.createCanvas();
        
        //tileEditor.start(JmeContext.Type.Canvas);
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                

                //EditorPanel test = new EditorPanel(tileEditor,currentMode.getWidth(),currentMode.getHeight());
                // test.setVisible(true);
                //test.startSwingEditor(tileEditor,currentMode.getWidth(),currentMode.getHeight());
               
               // frameEditor = new EditorWindowView(tileEditor, 640, 480);
               // frameEditor.startSwingEditor(true);
                EditorWindowSettings_View settingsView = new EditorWindowSettings_View();
                EditorWindowSettings_Model settingModel = new EditorWindowSettings_Model();
                settingControl = new EditorWindowSettings_Controller(settingModel, settingsView,tileEditor);
                
                
                
                //mainPanel = new MainPanel(tileEditor,800,600);
                //Runs the SimpleApplication SimpleInitApp()
                // 

            }
        });
        
        
        
        
    }
    
}
