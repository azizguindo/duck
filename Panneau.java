package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class Panneau {
    private Body objet;
    private PolygonShape shape;
    private Texture panneau;
    private TextureFactory textureFactory;

    public Panneau(){
        shape = new PolygonShape();
        float[] pts ={0,0,0,35,15,35,15,0};
        shape.set(pts);
        textureFactory = new TextureFactory();
    }

    public void create(Monde monde) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        objet = monde.getWorld().createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.5f;
        objet.createFixture(fixtureDef);
        shape.dispose();
    }

    public void draw(SpriteBatch sb){
        panneau = textureFactory.getEditPanel();
        Vector2 v = objet.getPosition();
        sb.draw(panneau,v.x,v.y,15,36);
    }
}
