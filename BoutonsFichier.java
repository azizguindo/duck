package fr.ul.duckseditor.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import fr.ul.duckseditor.DataFactory.TextureFactory;

public class BoutonsFichier {
    private Body objet1;
    private Body objet2;
    private Body objet3;
    private CircleShape shape1;
    private CircleShape shape2;
    private CircleShape shape3;
    private Texture boutonLoad;
    private Texture boutonSave;
    private Texture boutonRewrite;
    private TextureFactory textureFactory;

    public BoutonsFichier(){
        shape1 = new CircleShape();
        shape1.setRadius(2);
        shape2 = new CircleShape();
        shape2.setRadius(2);
        shape3 = new CircleShape();
        shape3.setRadius(2);
        textureFactory = new TextureFactory();
    }

    public void create(Monde monde) {
        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.StaticBody;
        bodyDef1.position.set(7.5f,28);
        objet1 = monde.getWorld().createBody(bodyDef1);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.density = 0.5f;
        fixtureDef1.restitution = 0.5f;
        objet1.createFixture(fixtureDef1);
        shape1.dispose();

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bodyDef2.position.set(10.5f,23);
        objet2 = monde.getWorld().createBody(bodyDef2);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        fixtureDef2.density = 0.5f;
        fixtureDef2.restitution = 0.5f;
        objet2.createFixture(fixtureDef2);
        shape2.dispose();

        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.StaticBody;
        bodyDef3.position.set(3.5f,23);
        objet3 = monde.getWorld().createBody(bodyDef3);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = 0.5f;
        fixtureDef3.restitution = 0.5f;
        objet3.createFixture(fixtureDef3);
        shape3.dispose();
    }

    public void draw(SpriteBatch sb){
        boutonLoad = textureFactory.getLoad();
        boutonRewrite = textureFactory.getRewrite();
        boutonSave = textureFactory.getSave();
        Vector2 v1 = objet1.getPosition();
        Vector2 v2 = objet2.getPosition();
        Vector2 v3 = objet3.getPosition();
        sb.draw(boutonLoad,v1.x-2,v1.y-2,4,4);
        sb.draw(boutonRewrite,v2.x-2,v2.y-2,4,4);
        sb.draw(boutonSave,v3.x-2,v3.y-2,4,4);
    }

    public Body getObjet1() {
        return objet1;
    }

    public Body getObjet2() {
        return objet2;
    }

    public Body getObjet3() {
        return objet3;
    }
}