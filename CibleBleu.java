package fr.ul.duckseditor.Model;

        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.math.Vector2;
        import com.badlogic.gdx.physics.box2d.Body;
        import com.badlogic.gdx.physics.box2d.BodyDef;
        import com.badlogic.gdx.physics.box2d.CircleShape;
        import com.badlogic.gdx.physics.box2d.FixtureDef;
        import fr.ul.duckseditor.DataFactory.TextureFactory;

public class CibleBleu extends Objet{
    private TextureFactory textureFactory;

    public CibleBleu(){
        shape = new CircleShape();
        shape.setRadius(1);
        textureFactory = new TextureFactory();
        texture = textureFactory.getTargetblue();
    }

    public void draw(SpriteBatch sb){
        Vector2 v = objet.getPosition();
        sb.draw(texture,v.x-1,v.y-1,2,2);
    }
}