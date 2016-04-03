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
public class SpriteCharacter
{

    /*
     * s_direction:
     *	if it's set to 2, then (-1)^n would be positive i.e. sprite moves to next frame (to the right)
     *	if it's set to 1, then (-1)^n would be negative i.e. sprite moves to previous frame (to the left)
     */
    private int s_currentFrame = 0, s_currentRow = 0;
    private int arrayFrameRow[];
    private float s_timeSeparation, s_time, s_stride[], s_depth[];
    private String s_name;
    private Geometry s_geometry;
    private float[][] s_uvTextureArray;
    //private float[][] s_uvTextureArrayBase;
    private VertexBuffer s_uvTexture;
    private Node s_node;
    private Texture s_spritesheet;
    private Material s_material;

    private enum QuadTexture
    {

        Base, Feet, Legs, Belt, Torso, Hands, Weapon, Head
    };

    public SpriteCharacter()
    {
    }

    /**
     * Creates a Sprite:
     *
     * @param imageLocation
     * @param name
     * @param assetManager
     * @param animated
     * @param transparent
     * @param frames
     * @param rows
     * @param timeSeparation
     * @param onEnd
     * @param onResume
     */
    public SpriteCharacter(String imageLocation,
                           String name,
                           AssetManager assetManager,
                           boolean transparent,
                           int arrayFrameRow[],
                           float timeSeparation, int maxFrames)
    {

        this.arrayFrameRow = new int[arrayFrameRow.length];
        System.arraycopy(arrayFrameRow, 0, this.arrayFrameRow, 0, arrayFrameRow.length);
        this.s_name = name;
        this.s_timeSeparation = timeSeparation;
        s_stride = new float[arrayFrameRow.length];
        s_depth = new float[arrayFrameRow.length];
        s_spritesheet = assetManager.loadTexture(imageLocation);
        int width = s_spritesheet.getImage().getWidth() / maxFrames;
        int height = s_spritesheet.getImage().getHeight() / arrayFrameRow.length;
        Quad s_quad = new Quad(width, height);

        s_geometry = new Geometry(name, s_quad);
        s_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        s_material.setTexture("ColorMap", s_spritesheet);
        s_geometry.setMaterial(s_material);
        if (transparent)
        {
            s_material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        }

        s_node = new Node(name);
        s_node.attachChild(s_geometry);

        s_uvTexture = s_geometry.getMesh().getBuffer(VertexBuffer.Type.TexCoord);
        s_uvTextureArray = new float[arrayFrameRow.length][];
        System.out.print(arrayFrameRow.length);
        for (int i = 0; i < arrayFrameRow.length; i++)
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
                        s_uvTextureArray[i][j] /= arrayFrameRow.length;
                        break;
                }
            }
            s_stride[i] = s_uvTextureArray[i][4];
            s_depth[i] = s_uvTextureArray[i][7];

            s_uvTextureArray[i][1] += s_depth[i] * i;
            s_uvTextureArray[i][3] += s_depth[i] * i;
            s_uvTextureArray[i][5] += s_depth[i] * i;
            s_uvTextureArray[i][7] += s_depth[i] * i;
        }
        //this.s_uvTextureArrayBase = new float[s_uvTextureArray.length][s_uvTextureArray[0].length];
        //System.arraycopy(s_uvTextureArray, 0, s_uvTextureArrayBase, 0, s_uvTextureArray.length);
        //s_uvTextureArrayBase = s_uvTextureArray;
        //s_uvTexture.updateData(BufferUtils.createFloatBuffer(s_uvTextureArray[0]));
    }

    private Geometry[] createQuads(int width, int height, AssetManager assetManager, String[] imageLocation)
    {
        Geometry[] geom = new Geometry[QuadTexture.values().length];
        Material[] mat = new Material[QuadTexture.values().length];
        Texture[] tex = new Texture[QuadTexture.values().length];
        Quad[] s_quad = new Quad[QuadTexture.values().length];
        ;
        for (int i = 0; i < QuadTexture.values().length; i++)
        {
            tex[i] = assetManager.loadTexture(imageLocation[i]);
            s_quad[i] = new Quad(width, height);
            geom[i] = new Geometry(QuadTexture.values()[i].name(), s_quad[i]);
            mat[i] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat[i].setTexture("ColorMap", tex[i]);
            geom[i].setMaterial(mat[i]);
            mat[i].getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        }


        return geom;
    }

    public void setCurrentFrame(int currentFrame)
    {
        s_currentFrame = currentFrame;
    }

    public void setCurrentRow(int currentRow)
    {
        s_currentRow = currentRow;
    }

    public void setTimeSeparation(int timeSeparation)
    {
        this.s_timeSeparation = timeSeparation;
    }

    public void setTime(float time)
    {
        this.s_time = time;
    }

    public void setName(String name)
    {
        this.s_name = name;
    }

    public int getFrames()
    {
        return arrayFrameRow[s_currentRow];
    }

    public int getCurrentFrame()
    {
        return s_currentFrame;
    }

    public int getCurrentRow()
    {
        return s_currentRow;
    }

    public float getTimeSeparation()
    {
        return s_timeSeparation;
    }

    public float getTime()
    {
        return s_time;
    }

    public String getName()
    {
        return s_name;
    }

    public Geometry getGeometry()
    {
        return s_geometry;
    }

    public float[] getVertexArray()
    {
        return s_uvTextureArray[s_currentRow];
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

    public void changeTexture(AssetManager assetManager, String imageLocation)
    {
        s_spritesheet = assetManager.loadTexture(imageLocation);
        s_material.setTexture("ColorMap", s_spritesheet);
        s_geometry.setMaterial(s_material);
    }

    public void changeRow()
    {
        s_currentRow++;
        /* s_uvTextureArray[s_currentRow][1] += s_depth[s_currentRow];
         s_uvTextureArray[s_currentRow][3] += s_depth[s_currentRow];
         s_uvTextureArray[s_currentRow][5] += s_depth[s_currentRow];
         s_uvTextureArray[s_currentRow][7] += s_depth[s_currentRow];
         */

        if (s_currentRow >= arrayFrameRow.length - 1)
        {
            s_currentRow = 0;
            //s_uvTextureArray[s_currentRow][1] = 0.0f;
            //s_uvTextureArray[s_currentRow][3] = 0.0f;
            // s_uvTextureArray[s_currentRow][5] = s_depth[s_currentRow];
            //s_uvTextureArray[s_currentRow][7] = s_depth[s_currentRow];

        }
    }

    public void update(float tpf)
    {
        //


        if (s_currentFrame < (arrayFrameRow[s_currentRow] - 1))
        {
            for (int a = 0; a < s_uvTextureArray[s_currentRow].length; a++)
            {

                if (a % 2 == 0)
                {
                    s_uvTextureArray[s_currentRow][a] = s_uvTextureArray[s_currentRow][a] + s_stride[a];
                }

            }
            s_uvTexture.updateData(BufferUtils.createFloatBuffer(s_uvTextureArray[s_currentRow]));
            s_time = 0;
            s_currentFrame += 1;

        }
        if (s_currentFrame == (arrayFrameRow[s_currentRow] - 1))
        {
            for (int a = 0; a < s_uvTextureArray[s_currentRow].length; a++)
            {
                if (a % 2 == 0)
                {
                    float test2 = s_currentFrame * s_stride[s_currentRow];
                    float test = ((float) (arrayFrameRow[s_currentRow] - 1) / arrayFrameRow[s_currentRow]);
                    s_uvTextureArray[s_currentRow][a] = s_uvTextureArray[s_currentRow][a] - test2;
                }
            }

            //s_uvTexture.updateData(BufferUtils.createFloatBuffer(s_uvTextureArray[s_currentRow]));
            s_time = 0;

            s_currentFrame = 0;
            // changeRow();
        }
    }
}