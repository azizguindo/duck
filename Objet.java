package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Align;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public abstract class Objet {
    protected Body objet;
    protected Shape shape;
    protected BodyDef bodyDef;
    protected Texture texture;
    protected Sprite sprite;
    protected TextureFactory textureFactory;
    protected BodyDef.BodyType type;

    public Objet(){
        bodyDef = new BodyDef();
    }

    public void create(Monde monde, BodyDef.BodyType type1,float X,float Y){
        type =type1;
        bodyDef.type = type;
        bodyDef.position.set(X,Y);
        objet=monde.getWorld().createBody(bodyDef );
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape;
        fixtureDef1.density = 1f;
        fixtureDef1.restitution = 0.5f;
        objet.createFixture(fixtureDef1);
        shape.dispose();
        objet.setUserData(this);
    }

    public Body getBody() {
        return objet;
    }

    public abstract void draw(SpriteBatch sb);

    public Sprite getSprite() {
        return sprite;
    }

    public void rotation(float angle){
        sprite.setRotation(angle);
    }

    public BodyDef.BodyType getType() {
        return type;
    }
}