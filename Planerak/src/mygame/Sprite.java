package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;
import java.nio.FloatBuffer;

/**
 * @author Nicholas Mamo - Nyphoon Games
 */
public class Sprite
{

    /*
     * s_direction:
     *	if it's set to 2, then (-1)^n would be positive i.e. sprite moves to next frame (to the right)
     *	if it's set to 1, then (-1)^n would be negative i.e. sprite moves to previous frame (to the left)
     */
    private boolean s_paused, s_animated;
    private int s_currentRow = 0, s_direction = 2, s_currentFrame = 0, s_frameWidth, s_maxFrames;
    private int[] framesPerRow;
    private float s_timeSeparation, s_time, s_stride[];
    private String s_onEnd, s_onResume, s_tempOnResume = "", s_name;
    private Geometry s_geometry;
    private float[][] s_uvTextureArray;
    private float[][] s_uvTextureArrayBase;
    private VertexBuffer s_uvTexture;
    private Node s_node;
    private Material s_material;
    private Texture s_spritesheet;
    private AssetManager assetManager;

    private enum QuadTexture
    {

        Base, Feet, Legs, Belt, Torso, Hands, Weapon, Head
    };

    public Sprite()
    {
    }

    public Sprite(Sprite copySprite)
    {
        this.s_animated = copySprite.s_animated;
        this.s_name = copySprite.s_name;
        this.s_timeSeparation = copySprite.s_timeSeparation;
        this.s_onEnd = copySprite.s_onEnd;
        this.s_onResume = copySprite.s_tempOnResume = copySprite.s_onResume;
        this.framesPerRow = copySprite.framesPerRow;
        this.s_maxFrames = copySprite.s_maxFrames;
        this.s_stride = copySprite.s_stride;
        this.assetManager = copySprite.assetManager;

    }

    public Sprite(String imageLocation,
                  String name,
                  AssetManager assetManager,
                  boolean animated,
                  boolean transparent,
                  int framesPerRow[],
                  int maxFrames,
                  float timeSeparation,
                  String onEnd,
                  String onResume, Node spriteNode)
    {
        //Initialize Sprite default values
        this.s_animated = animated;
        this.s_name = name;
        this.s_timeSeparation = timeSeparation;
        this.s_onEnd = onEnd;
        this.s_onResume = this.s_tempOnResume = onResume;
        this.framesPerRow = framesPerRow;
        this.s_maxFrames = maxFrames;
        this.s_stride = new float[framesPerRow.length];
        this.assetManager = assetManager;



        //Create Texture from ImageLocation
        s_spritesheet = assetManager.loadTexture(imageLocation);
        int width = s_spritesheet.getImage().getWidth() / maxFrames;
        int height = s_spritesheet.getImage().getHeight() / framesPerRow.length;

        //Create Quad based on Image Width and Height
        //framesPerRow.length is the total number of rows that exist within the image. framesPerRow[0] is the number of frames in that row
        Quad s_quad = new Quad((int) (s_spritesheet.getImage().getWidth() / maxFrames), (int) (s_spritesheet.getImage().getHeight() / framesPerRow.length));
        //Create the Material that will display the texture.
        s_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        s_material.setTexture("ColorMap", s_spritesheet);
        //Create a new Geormetry object that can be displayed in the jmonkey scene graph.
        s_geometry = new Geometry(name, s_quad);
        //Assign material to Geometry.
        s_geometry.setMaterial(s_material);
        if (transparent)
        {
            //ensures the alpha layer is used.
            s_material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        }

        //Geometry[] see = createQuads(width, height, assetManager);
        if (spriteNode != null)
        {
            s_node = spriteNode;
        } else
        {
            s_node = new Node(name);
        }

        s_node.attachChild(s_geometry);

        //get the texture coordinates from the vertex buffer.
        s_uvTexture = s_geometry.getMesh().getBuffer(VertexBuffer.Type.TexCoord);
        s_uvTextureArray = new float[framesPerRow.length][];
        for (int i = 0; i < framesPerRow.length; i++)
        {
            s_uvTextureArray[i] = BufferUtils.getFloatArray((FloatBuffer) s_uvTexture.getData());
            for (int j = 0; j < s_uvTextureArray[i].length; j++)
            {
                switch (j)
                {
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                        s_uvTextureArray[i][j] /= maxFrames;
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                        s_uvTextureArray[i][j] /= framesPerRow.length;
                        break;
                }
            }
            s_stride[i] = s_uvTextureArray[i][4];
            float depth = s_uvTextureArray[i][7];
            s_uvTextureArray[i][1] += depth * i;
            s_uvTextureArray[i][3] += depth * i;
            s_uvTextureArray[i][5] += depth * i;
            s_uvTextureArray[i][7] += depth * i;
            if (onEnd.equals("Scroll"))
            {
                this.s_frameWidth = this.framesPerRow[s_currentRow];
                this.framesPerRow[s_currentRow] = s_spritesheet.getImage().getWidth();
            }


            //s_uvTexture.updateData(BufferUtils.createFloatBuffer(s_uvTextureArray[s_currentRow]));
        }
        s_uvTextureArrayBase = new float[s_uvTextureArray.length][s_uvTextureArray[0].length];

        for (int k = 0; k < s_uvTextureArray.length; k++)
        {
            System.arraycopy(s_uvTextureArray[k], 0, s_uvTextureArrayBase[k], 0, s_uvTextureArray[0].length);
        }
        System.out.print("");
    }

    private Geometry[] createQuads(int width, int height, AssetManager assetManager)
    {
        Geometry[] geom = new Geometry[QuadTexture.values().length];
        Material[] mat = new Material[QuadTexture.values().length];
        Texture[] tex = new Texture[QuadTexture.values().length];
        Quad[] s_quad = new Quad[QuadTexture.values().length];
        String[] temp3 = new String[]
        {
            "Textures/LPC Medieval Fantasy/png/characters/LPC_DarkElf_Female_1.png",
            "Textures/LPC Medieval Fantasy/png/feet/shoes/male/brown_shoes_male.png",
            "Textures/LPC Medieval Fantasy/png/legs/skirt/male/robe_skirt_male.png",
            "Textures/LPC Medieval Fantasy/png/belt/leather/male/leather_male.png",
            "Textures/LPC Medieval Fantasy/png/torso/leather/chest_male.png",
            "Textures/LPC Medieval Fantasy/png/hands/bracers/male/leather_bracers_male.png",
            "Textures/LPC Medieval Fantasy/png/weapons/right hand/either/bow.png",
            "Textures/LPC Medieval Fantasy/png/head/caps/male/leather_cap_male.png"
        };
        for (int i = 0; i < QuadTexture.values().length; i++)
        {
            tex[i] = assetManager.loadTexture(temp3[i]);
            s_quad[i] = new Quad(width, height);
            geom[i] = new Geometry(QuadTexture.values()[i].name(), s_quad[i]);
            mat[i] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat[i].setTexture("ColorMap", tex[i]);
            geom[i].setMaterial(mat[i]);
            mat[i].getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        }


        return geom;
    }

    public void setPaused(boolean paused)
    {
        /*
         * When paused is set to false, i.e. animation is set to resume/play, check whether or not onResume has been changed (onResume != tempOnResume). 
         *      If it hasn't, then just play;
         *      Else set to play and then replace onResume by tempOnResume
         */
        this.s_paused = paused;
        if (!s_paused && !(s_onResume.equals(s_tempOnResume)))
        {
            s_onResume = s_tempOnResume;
        } else if (!s_paused && (s_onResume.equals("Start")))
        {
            if (s_onEnd.equals("Scroll"))
            {
                s_currentFrame = 0;
                s_time = 0;
                s_uvTextureArray[s_currentRow] = BufferUtils.getFloatArray((FloatBuffer) s_uvTexture.getData());
                for (int j = 0; j < s_uvTextureArray.length; j++)
                {
                    if (j == 0 || j == 6)
                    {
                        s_uvTextureArray[s_currentRow][j] = 0;
                    } else if (j % 2 == 0)
                    {
                        s_uvTextureArray[s_currentRow][j] = 1f / s_frameWidth;
                    }
                }
                s_uvTexture.updateData(BufferUtils.createFloatBuffer(s_uvTextureArray[s_currentRow]));
            } else
            {
                s_currentFrame = 0;
                s_time = 0;
                s_uvTextureArray[s_currentRow] = BufferUtils.getFloatArray((FloatBuffer) s_uvTexture.getData());
                for (int j = 0; j < s_uvTextureArray.length; j++)
                {
                    if (j == 0 || j == 6)
                    {
                        s_uvTextureArray[s_currentRow][j] = 0;
                    } else if (j % 2 == 0)
                    {
                        s_uvTextureArray[s_currentRow][j] = 1f / framesPerRow[s_currentRow];
                    }
                }
                s_uvTexture.updateData(BufferUtils.createFloatBuffer(s_uvTextureArray[s_currentRow]));
            }
        }
    }

    public void setFrames(int frames)
    {
        framesPerRow[s_currentRow] = frames;
    }

    public void changeDirection()
    {
        if (s_direction == 2)
        {
            s_direction = 1;
        } else
        {
            s_direction = 2;
        }
    }

    public void changeRow(int row)
    {
        this.s_currentRow = row;
    }

    public void changeRow()
    {
        if (this.s_currentRow + 1 < framesPerRow.length)
        {
            this.s_currentRow++;
        } else
        {
            this.s_currentRow = 0;
        }

    }

    public void changeBaseTexture(String imageLocation)
    {
        s_spritesheet = assetManager.loadTexture(imageLocation);
        s_material.setTexture("ColorMap", s_spritesheet);
        s_geometry.setMaterial(s_material);
    }

    public void setCurrentFrame(int currentFrame)
    {
        s_currentFrame = currentFrame;
    }

    public void setTimeSeparation(int timeSeparation)
    {
        this.s_timeSeparation = timeSeparation;
    }

    public void setTime(float time)
    {
        this.s_time = time;
    }

    public void setOnEnd(String onEnd)
    {
        /*
         * Possible values: 
         *      "Loop"      - Start from the beginning
         *      "NoLoop"    - Stop animation (setPaused(true)), store onResume into tempOnResume and update onResume to start all over
         *      "Reverse"   - Go back, restart upon reaching beginning
         *	"Scroll"    - Scroll, like in a side-scrolling texture. In this case, frames = texture.width
         */
        this.s_onEnd = onEnd;
    }

    public void setOnResume(String onResume)
    {
        /*
         * Possible values: 
         *      "Continue"  - Continue from current frame
         *      "Start"     - Start from the beginning
         */
        this.s_onResume = onResume;
    }

    public void setName(String name)
    {
        this.s_name = name;
    }

    public boolean getPaused()
    {
        return s_paused;
    }

    public boolean getAnimated()
    {
        return s_animated;
    }

    public int getMaxFrames()
    {
        return s_maxFrames;
    }

    public int getFrames()
    {
        return framesPerRow[s_currentRow];
    }

    public int getDirection()
    {
        return s_direction;
    }

    public int getCurrentFrame()
    {
        return s_currentFrame;
    }

    public int getFrameWidth()
    {
        return s_frameWidth;
    }

    public float getTimeSeparation()
    {
        return s_timeSeparation;
    }

    public float getTime()
    {
        return s_time;
    }

    public String getOnEnd()
    {
        return s_onEnd;
    }

    public String getOnResume()
    {
        return s_onResume;
    }

    public String getName()
    {
        return s_name;
    }

    public float getStride()
    {
        return s_stride[s_currentRow];
    }

    public Geometry getGeometry()
    {
        return s_geometry;
    }

    public float[] getVertexArray()
    {
        return s_uvTextureArray[s_currentRow];
    }

    public float[] getBaseVertexArray()
    {
        return s_uvTextureArrayBase[s_currentRow];
    }

    public VertexBuffer getVertexBuffer()
    {
        return s_uvTexture;
    }

    public Node getNode()
    {
        return s_node;
    }

    public void updateVertexArray(int index, float value)
    {
        s_uvTextureArray[s_currentRow][index] = value;
    }

    public void move(int x, int y)
    {
        s_node.setLocalTranslation(s_node.getLocalTranslation().x + x, s_node.getLocalTranslation().y + y, s_node.getLocalTranslation().z);
    }

    public void moveAbsolute(int x, int y)
    {
        s_node.setLocalTranslation(x, y, s_node.getLocalTranslation().z);
    }

    public void setOrder(int z)
    {
        s_node.setLocalTranslation(s_node.getLocalTranslation().x, s_node.getLocalTranslation().y, s_node.getLocalTranslation().z + z);
    }

    public void setOrderAbsolute(int z)
    {
        s_node.setLocalTranslation(s_node.getLocalTranslation().x, s_node.getLocalTranslation().y, z);
    }

    public void rotate(int x, int y, int z)
    {
        s_node.rotate(FastMath.DEG_TO_RAD * x, FastMath.DEG_TO_RAD * y, FastMath.DEG_TO_RAD * z);
    }
}