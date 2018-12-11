package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class Heros extends Objet {

    private TextureFactory textureFactory;

    public Heros(){
        shape = new CircleShape();
        shape.setRadius(1);
        textureFactory = new TextureFactory();
        texture = textureFactory.getDuck();
    }

    public void draw(SpriteBatch sb){
        Vector2 v = objet.getPosition();
        sb.draw(texture,v.x-1,v.y-1,2,2);
    }
}