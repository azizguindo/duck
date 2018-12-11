package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class BoutonPlay {
    private Body objet;
    private CircleShape shape;
    private Texture texture;
    private TextureFactory textureFactory;
    private Sprite sprite;

    public BoutonPlay(){
        shape = new CircleShape();
        shape.setRadius(2);
        textureFactory = new TextureFactory();
    }

    public void create(Monde monde) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(7.5f,33);
        objet = monde.getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.5f;
        objet.createFixture(fixtureDef);
        texture = textureFactory.getPlay();
        sprite = new Sprite(texture);
        sprite.setSize(4,4);
        shape.dispose();
    }

    public void draw(SpriteBatch sb){
        sprite.setPosition(objet.getPosition().x-2,objet.getPosition().y-2);
        sprite.draw(sb);

    }

    public Sprite getSprite() {
        return sprite;
    }

    public Body getObjet() {
        return objet;
    }
}
