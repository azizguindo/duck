package fr.ul.duckseditor.Model;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class CibleBeige extends Objet {

    private TextureFactory textureFactory;

    public CibleBeige(){
        shape = new CircleShape();
        shape.setRadius(1);
        textureFactory = new TextureFactory();
        texture = textureFactory.getTargetbeige();
        sprite = new Sprite(texture);
        sprite.setSize(2,2);

    }

    public void draw(SpriteBatch sb){
        sprite.setOrigin(objet.getLocalCenter().x,objet.getLocalCenter().y);
        sprite.setPosition(objet.getPosition().x-1,objet.getPosition().y-1);
        sprite.draw(sb);
    }
}