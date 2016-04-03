package mygame;

import com.jme3.scene.Node;
import java.util.ArrayList;

public class SpriteLibraryCharacter
{

    static Node l_guiNode;
    private boolean l_staticLibrary;
    private Node l_node = new Node("");
    private ArrayList<SpriteCharacter> l_library = new ArrayList<SpriteCharacter>();

    public SpriteLibraryCharacter(String name, boolean staticLibrary)
    {
        l_node.setName(name);
        this.l_staticLibrary = staticLibrary;
    }

    public void setStatic(boolean staticLibrary)
    {
        this.l_staticLibrary = staticLibrary;
    }

    public void addSprite(SpriteCharacter sprite)
    {
        l_library.add(sprite);
        l_node.attachChild(sprite.getNode());
    }

    public void removeSprite(String name)
    {
        for (int i = 0; i < l_library.size(); i++)
        {
            if (l_library.get(i).getName().equals(name))
            {
                l_library.remove(i);
            }
        }
    }

    public boolean getStatic()
    {
        return l_staticLibrary;
    }

    public Node getNode()
    {
        return l_node;
    }

    public ArrayList<SpriteCharacter> getLibrary()
    {
        return l_library;
    }

    public SpriteCharacter getSprite(int index)
    {
        return l_library.get(index);
    }

    public SpriteCharacter getSprite(String name)
    {
        for (int i = 0; i < l_library.size(); i++)
        {
            if (this.l_library.get(i).getName().equals(name))
            {
                return this.l_library.get(i);
            }
        }
        return null;
    }

    public String getName()
    {
        return l_node.getName();
    }
}