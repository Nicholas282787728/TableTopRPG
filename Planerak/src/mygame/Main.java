package mygame;

import com.jme3.app.SimpleApplication;

import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

import com.jme3.math.Ray;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import com.jme3.texture.Texture;

public class Main extends SimpleApplication
{

    static Main app;
    Sprite sprite;
    Sprite sprite2;
    Sprite sprite3;
    AnimatedTile tile;
    TerrainGrid grid;
    static int x, y;
    int temp = 0;

    public static void main(String[] args)
    {
        app = new Main();
        app.setDisplayFps(false);
        app.setDisplayStatView(false);

        app.start();
    }
    static SpriteEngine engine = new SpriteEngine();

    @Override
    public void simpleInitApp()
    {
        //sprite = new Sprite("Textures/Sprite.png", "Sprite 1", assetManager, true, true, 9, 1, 0.015f, "Loop", "Start");
        //int array[] = new int[]{7,7,7,7,8,8,8,8,9,9,9,9,6,6,6,6,13,13,13,13,6};
        int array[] = new int[]
        {
            6, 13, 13, 13, 13, 6, 6, 6, 6, 9, 9, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7
        };
        grid = new TerrainGrid(32, 32, "Test", assetManager);
        String[] temp3 = new String[]
        {
            "Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png",
            "Textures/LPC Medieval Fantasy/png/hurt/FEET_shoes_brown.png",
            "Textures/LPC Medieval Fantasy/png/hurt/LEGS_robe_skirt.png",
            "Textures/LPC Medieval Fantasy/png/hurt/BELT_leather.png",
            "Textures/LPC Medieval Fantasy/png/hurt/TORSO_leather_armor_torso.png",
            "Textures/LPC Medieval Fantasy/png/hurt/HANDS_plate_armor_gloves.png",
            "Textures/LPC Medieval Fantasy/png/slash/WEAPON_dagger.png",
            "Textures/LPC Medieval Fantasy/png/hurt/HEAD_leather_armor_hat.png"
        };

        sprite //= new SpriteMulti("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png", "Sprite 1", assetManager,true,true, array,13, 0.25f,"Loop", "Start");
                = new Sprite("Textures/LPC_DarkElf_Female_1.png", "Sprite 1", assetManager, true, true, array, 13, 0.25f, "Loop", "Start", null);

        tile = new AnimatedTile(array, "Sprite 1", "Textures/LPC_DarkElf_Female_1.png", assetManager);
        // sprite2 = new Sprite("Textures/LPC Medieval Fantasy/png/feet/shoes/male/brown_shoes_male.png", "Sprite 2", assetManager,true,true, array,13, 0.25f,"Loop", "Start",sprite.getNode());
        // sprite3 = new Sprite("Textures/LPC Medieval Fantasy/png/torso/leather/chest_male.png", "Sprite 3", assetManager,true,true, array,13, 0.25f,"Loop", "Start",sprite.getNode());
        //sprite = new SpriteCharacter("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png", "Sprite 1", assetManager,true, array, 0.15f,13);
        inputManager.setCursorVisible(true);
        flyCam.setEnabled(false);
        guiNode.attachChild(grid.getNode());
        guiNode.attachChild(tile.getNode());

        tile.setMinFrame(10, 1);
        tile.setMinFrame(12, 1);
        //tile.setTimeBetweenFrames(0.15f);
        //SpriteLibraryCharacter.l_guiNode = guiNode;
        //SpriteLibraryCharacter library = new SpriteLibraryCharacter("Library 1", false);
        //SpriteLibrary.l_guiNode = guiNode;
        //SpriteLibrary library = new SpriteLibrary("Library 1", false);
        //library.addSprite(sprite);
        //library.addSprite(sprite2);
        //library.addSprite(sprite3);
        //engine = new SpriteEngine(sprite);
        //engine.addLibrary(library);
        inputManager.addMapping("Exit", new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addMapping("Move_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Move_Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Move_Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Move_Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Next_Frame", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Previous_Frame", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Change_Texture", new KeyTrigger(KeyInput.KEY_X));
        inputManager.addMapping("Change_Row", new KeyTrigger(KeyInput.KEY_V));
        inputManager.addMapping("Shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Change_Texture");
        inputManager.addListener(actionListener, "Change_Row");
        inputManager.addListener(actionListener, "Next_Frame");
        inputManager.addListener(actionListener, "Previous_Frame");

        inputManager.addListener(actionListener, "Shoot");
        inputManager.addListener(analogListener, "Move_Up");
        inputManager.addListener(analogListener, "Move_Down");
        inputManager.addListener(analogListener, "Move_Left");
        inputManager.addListener(analogListener, "Move_Right");







        x = app.settings.getWidth() / 2;
        y = app.settings.getHeight() / 2;
        //test.moveAbsolute(x, y);
        tile.moveAbsolute(x, y);




    }

    @Override
    public void simpleUpdate(float tpf)
    {
        //sprite.update(tpf);
        tile.setTime(tile.getTime() + tpf);
        if (tile.getTime() >= tile.getTimeBetweenFrames())
        {
            tile.nextFrame();
        }
        //engine.update(tpf);
    }
    private AnalogListener analogListener = new AnalogListener()
    {
        public void onAnalog(String name, float keyPressed, float tpf)
        {
            /**
             * TODO: test for mapping names and implement actions
             */
            if (name.equalsIgnoreCase("Move_Up"))
            {

                grid.move(0, 10);


            }
            if (name.equalsIgnoreCase("Move_Down"))
            {

                grid.move(0, -10);



            }
            if (name.equalsIgnoreCase("Move_Left"))
            {

                grid.move(-10, 0);



            }
            if (name.equalsIgnoreCase("Move_Right"))
            {

                grid.move(10, 0);



            }
        }
    };
    private ActionListener actionListener = new ActionListener()
    {
        public void onAction(String name, boolean pressed, float tpf)
        {

            if (name.equals("Shoot") && pressed)
            {
                // 1. Reset results list.
                CollisionResults results = new CollisionResults();
                // 2. Aim the ray from cam loc to cam direction.

                Ray mouseRay = new Ray(new Vector3f(inputManager.getCursorPosition().x, inputManager.getCursorPosition().y, 1000), new Vector3f(0, 0, -1));
                //Ray ray = new Ray(cam.getLocation(), click3d);

                // System.out.println(dir);
                System.out.println(mouseRay);
                // 3. Collect intersections between Ray and Shootables in results list.
                Node test = grid.getNode();
                test.collideWith(mouseRay, results);

                // 4. Print the results
                System.out.println("----- Collisions? " + results.size() + "-----");
                for (int i = 0; i < results.size(); i++)
                {
                    // For each hit, we know distance, impact point, name of geometry.

                    float dist = results.getCollision(i).getDistance();
                    Vector3f pt = results.getCollision(i).getContactPoint();
                    String hit = results.getCollision(i).getGeometry().getName();
                    System.out.println(guiNode.getChild(hit).getControl(TerrainTile.class));
                    System.out.println();

                    if (guiNode.getChild(hit).getControl(TerrainTile.class) != null)
                    {
                        TerrainTile te = guiNode.getChild(hit).getControl(TerrainTile.class);
                        te.nextFrame();
                    }
                    System.out.println("* Collision #" + i);
                    System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
                }
            }
            if (name.equalsIgnoreCase("Move_Up") && pressed)
            {

                tile.move(0, 64);


            }
            if (name.equalsIgnoreCase("Next_Frame") && pressed)
            {
            }
            if (name.equalsIgnoreCase("Previous_Frame") && pressed)
            {
                tile.previousFrame();
            }
            if (name.equalsIgnoreCase("Change_Row") && pressed)
            {


                tile.changeRow();

            }
            if (name.equalsIgnoreCase("Change_Texture") && pressed)
            {
                switch (temp)
                {
                    case 0:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_2.png");
                        break;
                    case 1:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Male_1.png");
                        break;
                    case 2:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Male_2.png");
                        break;
                    case 3:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Light.png");
                        break;
                    case 4:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Tanned.png");
                        break;
                    case 5:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Tanned2.png");
                        break;
                    case 6:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Human_Dark_Male.png");
                        break;
                    case 7:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Human_Female_Dark.png");
                        break;
                    case 8:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Male_Orc.png");
                        break;
                    case 9:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Orc.png");
                        break;
                    case 10:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Skeleton.png");
                        break;
                    case 11:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Tanned_Male.png");
                        break;
                    case 12:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Tanned_Male_2.png");
                        break;
                    case 13:
                        tile.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png");
                        temp = 0;
                        break;


                }
                temp++;
            }


        }
    };
}