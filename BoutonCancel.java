package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class BoutonCancel {
    private Body objet;
    private PolygonShape shape;
    private Texture panneau;
    private TextureFactory textureFactory;

    public BoutonCancel(){
        shape = new PolygonShape();
        float[] pts = {0,0,0,4,4,4,4,0};
        shape.set(pts);
        textureFactory = new TextureFactory();
    }

    public void create(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(31f,1);
        objet = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.5f;
        objet.createFixture(fixtureDef);
        shape.dispose();
    }

    public void draw(SpriteBatch sb){
        panneau = textureFactory.getCancel();
        Vector2 v = objet.getPosition();
        sb.draw(panneau,v.x,v.y,4,4);
    }

    public Body getObjet() {
        return objet;
    }
}
