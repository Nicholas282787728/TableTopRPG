/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author K
 */
public class CharacterTile
{

    AnimatedTile BaseCharacter;
    int temp = 0;

    public CharacterTile(int[] array, AssetManager assetManager, int x, int y, InputManager inputManager)
    {

        BaseCharacter = new AnimatedTile(array, "Sprite 1", "Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png", assetManager);
        BaseCharacter.setMinFrame(10, 1);
        BaseCharacter.setMinFrame(12, 1);

        inputManager.addMapping("Exit", new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addMapping("Move_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Move_Down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Move_Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Move_Right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Next_Frame", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Previous_Frame", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Change_Texture", new KeyTrigger(KeyInput.KEY_X));
        inputManager.addMapping("Change_Row", new KeyTrigger(KeyInput.KEY_V));
        inputManager.addListener(actionListener, "Change_Texture");
        inputManager.addListener(actionListener, "Change_Row");
        inputManager.addListener(actionListener, "Next_Frame");
        inputManager.addListener(actionListener, "Previous_Frame");

        inputManager.addListener(actionListener, "Move_Up");
        inputManager.addListener(analogListener, "Move_Down");
        inputManager.addListener(analogListener, "Move_Left");
        inputManager.addListener(analogListener, "Move_Right");
    }

    public void moveUp()
    {
        BaseCharacter.move(0, 64);
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

                BaseCharacter.move(0, 64);


            }
            if (name.equalsIgnoreCase("Move_Down"))
            {

                BaseCharacter.move(0, -1);



            }
            if (name.equalsIgnoreCase("Move_Left"))
            {

                BaseCharacter.move(-1, 0);



            }
            if (name.equalsIgnoreCase("Move_Right"))
            {

                BaseCharacter.move(1, 0);



            }
        }
    };
    private ActionListener actionListener = new ActionListener()
    {
        public void onAction(String name, boolean pressed, float tpf)
        {

            if (name.equalsIgnoreCase("Move_Up") && pressed)
            {

                BaseCharacter.moveSmooth(0, 1, tpf);


            }
            if (name.equalsIgnoreCase("Next_Frame") && pressed)
            {
            }
            if (name.equalsIgnoreCase("Previous_Frame") && pressed)
            {
                BaseCharacter.previousFrame();
            }
            if (name.equalsIgnoreCase("Change_Row") && pressed)
            {


                BaseCharacter.changeRow();

            }
            if (name.equalsIgnoreCase("Change_Texture") && pressed)
            {
                switch (temp)
                {
                    case 0:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_2.png");
                        break;
                    case 1:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Male_1.png");
                        break;
                    case 2:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Male_2.png");
                        break;
                    case 3:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Light.png");
                        break;
                    case 4:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Tanned.png");
                        break;
                    case 5:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Tanned2.png");
                        break;
                    case 6:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Human_Dark_Male.png");
                        break;
                    case 7:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Human_Female_Dark.png");
                        break;
                    case 8:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Male_Orc.png");
                        break;
                    case 9:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Female_Orc.png");
                        break;
                    case 10:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Skeleton.png");
                        break;
                    case 11:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Tanned_Male.png");
                        break;
                    case 12:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_Tanned_Male_2.png");
                        break;
                    case 13:
                        BaseCharacter.changeBaseTexture("Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png");
                        temp = 0;
                        break;


                }
                temp++;
            }


        }
    };
}
