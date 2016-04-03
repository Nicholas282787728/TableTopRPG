/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor;



import TerrainEditor.CustomFiles.TilesetLoader;
import TerrainEditor.JavaFX.FrameEditor;
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
    static FrameEditor frameEditor;
    private static DisplayMode currentMode;
    static SwingActionListener swingActionListener;
    
    @Override
    public void simpleInitApp()
    {
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
        
        inputManager.addMapping("Select_Tile", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(swingActionListener, "Select_Tile");
        

        
        inputManager.addMapping("Move_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addListener(swingActionListener, "Move_Up");
        
        inputManager.addMapping("Move_Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener(swingActionListener, "Move_Down");
        
        inputManager.addMapping("Move_Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addListener(swingActionListener, "Move_Left");
        
        inputManager.addMapping("Move_Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addListener(swingActionListener, "Move_Right");
        
        inputManager.setCursorVisible(true);
        flyCam.setEnabled(false);

        //Create Default Directories
        
        String userHome = System.getProperty("user.home");
        userHome += "\\Planerrak";        
        String tilesets = userHome + "\\Tilesets\\Data";
        String sprites = userHome + "\\Sprites";
        
        Boolean homeFolder = new File(userHome).mkdir();
        Boolean tilesetFolder = new File(tilesets).mkdirs();
        Boolean spriteFolder = new File(sprites).mkdir();
        

        System.out.println(userHome);
                //AssetManager ass = application.getAssetManager();
               
        tileEditor.getAssetManager().registerLocator(userHome, FileLocator.class);
        tileEditor.getAssetManager().registerLoader(TilesetLoader.class, "TileSet");
        //MainPanel panel = new MainPanel(tileEditor,currentMode.getWidth(),currentMode.getHeight());
        //panel = new TerrainPanel(32, 32,32,32, "Panel",assetManager,"Textures/LPC Terrain/terrain_atlas.png");
        //guiNode.attachChild(panel.getNode());
        frameEditor.setVisible(true);
        swingActionListener.setApplication(tileEditor);
    }
    
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
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

                //EditorPanel test = new EditorPanel(tileEditor,currentMode.getWidth(),currentMode.getHeight());
                // test.setVisible(true);
                //test.initialize(tileEditor,currentMode.getWidth(),currentMode.getHeight());
                swingActionListener = new SwingActionListener();
                frameEditor = new FrameEditor(tileEditor,swingActionListener, currentMode.getWidth(), currentMode.getHeight());
                frameEditor.initialize(false);


                //mainPanel = new MainPanel(tileEditor,800,600);
                //Runs the SimpleApplication SimpleInitApp()
                // tileEditor.start();

            }
        });
    }
    
}
