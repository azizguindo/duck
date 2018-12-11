package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class BlocRectangle extends Objet {
    private TextureFactory textureFactory;

    public BlocRectangle(){
        shape = new PolygonShape();
        float[] pts ={0,0,0,4,1,4,1,0};
        ((PolygonShape) shape).set(pts);
        textureFactory = new TextureFactory();
        texture = textureFactory.getBeam();
        sprite = new Sprite(texture);
        sprite.setSize(1,4);

    }

    public void draw(SpriteBatch sb){
        sprite.setOrigin(objet.getLocalCenter().x,objet.getLocalCenter().y);
        sprite.setPosition(objet.getPosition().x,objet.getPosition().y);
        sprite.draw(sb);
    }
}