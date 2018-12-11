package fr.ul.duckseditor.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.ul.duckseditor.DataFactory.TextureFactory;
import fr.ul.duckseditor.listener.Listener;

import java.util.ArrayList;

public class Monde {
    private World world;

    private int largeur;
    private int hauteur;
    private TextureFactory textureFactory;
    private Heros heros;
    private Bord bord;
    private Panneau panneau;
    private BoutonPlay boutonPlay;
    private BoutonTrash boutonTrash;
    private BoutonsFichier boutonsFichier;
    private BlocRectangle blocRectangle;
    private Bloc bloc;
    private CibleBeige cibleBeige;
    private SpriteBatch spriteBatch;
    private ArrayList<Objet> lesObjets;
    private ArrayList<Body> aSupprimer;


    public Monde() {
        world = new World(new Vector2(0, -10f), true);
        largeur = 64;
        hauteur = 36;
        textureFactory = new TextureFactory();
        panneau = new Panneau();
        boutonPlay = new BoutonPlay();
        boutonTrash = new BoutonTrash();
        boutonsFichier = new BoutonsFichier();
        blocRectangle = new BlocRectangle();
        bloc = new Bloc();
        cibleBeige = new CibleBeige();
        bord = new Bord();
        heros = new Heros();
        lesObjets = new ArrayList<Objet>();
        aSupprimer = new ArrayList<Body>();
    }

    public void create() {
        panneau.create(this);
        boutonPlay.create(this);
        boutonTrash.create(this);
        boutonsFichier.create(this);
        blocRectangle.create(this, BodyDef.BodyType.StaticBody, 7, 16);
        bloc.create(this, BodyDef.BodyType.StaticBody, 6.5f, 13);
        cibleBeige.create(this, BodyDef.BodyType.StaticBody, 7.5f, 10);
        heros.create(this, BodyDef.BodyType.DynamicBody, 32, 18);
        bord.create(this);
        spriteBatch = new SpriteBatch();
    }

    public void draw(SpriteBatch sb) {
        spriteBatch = sb;
        bord.draw(sb);
        panneau.draw(sb);
        boutonPlay.draw(sb);
        boutonTrash.draw(sb);
        boutonsFichier.draw(sb);
        blocRectangle.draw(sb);
        bloc.draw(sb);
        cibleBeige.draw(sb);
        heros.draw(sb);

        for(Objet objet : lesObjets){
            objet.draw(sb);
        }
        render();
    }

    public void render() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    public void ajouterObjet(Objet objet) {
        lesObjets.add(objet);
    }

    public World getWorld() {
        return world;
    }


    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public TextureFactory getTextureFactory() {
        return textureFactory;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public BlocRectangle getBlocRectangle() {
        return blocRectangle;
    }

    public CibleBeige getCibleBeige() {
        return cibleBeige;
    }

    public BoutonsFichier getBoutonsFichier() {
        return boutonsFichier;
    }

    public BoutonPlay getBoutonPlay() {
        return boutonPlay;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public ArrayList<Objet> getLesObjets() {
        return lesObjets;
    }

    public boolean contient(Body body){
        boolean contient = false;
        for (Objet objet : lesObjets){
            if(objet.getBody()==body){
                contient = true;
            }
        }
        return contient;
    }

    public Objet getObjet(Body body){
        Objet monObjet = null;
        for (Objet objet : lesObjets) {
            if (objet.getBody() == body) {
                monObjet =objet;
            }
        }
        return monObjet;
    }

    public BoutonTrash getBoutonTrash() {
        return boutonTrash;
    }

    public void supprimerObjet(ArrayList<Body> bodyArrayList){
        aSupprimer=bodyArrayList;
        for(Body body: aSupprimer) {
            Objet o = null;
            for (Objet objet : lesObjets) {
                if(objet.getBody()==body){
                    o=objet;
                    world.destroyBody(body);
                }

            }
            if(o!=null){
                getLesObjets().remove(o);
            }
        }
    }

    public void load(String filename) {
        String[] str=Gdx.files.absolute(filename).readString().split("\n");
        lesObjets=new ArrayList<Objet>();
        for(String line:str)
        {
            String[] att=line.split(";");
            if(att.length!=5)
                continue;
            String className=att[0];
            float x=Float.parseFloat(att[1]);
            float y=Float.parseFloat(att[2]);
            Objet o=null;
            if (className.trim().compareToIgnoreCase(Bloc.class.toString())==0) {
                o =new Bloc();
                o.create(this, BodyDef.BodyType.DynamicBody,x,y);
            } else if (className.trim().compareToIgnoreCase(BlocRectangle.class.toString())==0) {
                o =new BlocRectangle();
                o.create(this, BodyDef.BodyType.DynamicBody,x,y);
            }else if(className.trim().compareToIgnoreCase(Heros.class.toString())==0){
                o=new Heros();
                o.create(this, BodyDef.BodyType.DynamicBody,x,y);
            }else if(className.trim().compareToIgnoreCase(CibleBeige.class.toString())==0){
                o=new CibleBeige();
                o.create(this, BodyDef.BodyType.DynamicBody,x,y);
            }
            lesObjets.add(o);
        }
    }

    public void save(String filename)
    {
        PixmapIO.writePNG(Gdx.files.absolute( filename+".png"),screenShot());
        FileHandle fileHandle=Gdx.files.absolute( filename+".mdl");
        fileHandle.writeString(saveObjets(),false);
    }

    public String saveObjets()
    {
        StringBuilder stringBuilder=new StringBuilder("");
        for(Objet objet:lesObjets) {
            stringBuilder.append(objet.getType()+";");
            stringBuilder.append(objet.getBody().getPosition().x+";");
            stringBuilder.append(objet.getBody().getPosition().y+";");
        }
        return  stringBuilder.toString();
    }

    public Pixmap screenShot()
    {
        byte[] pixels= ScreenUtils.getFrameBufferPixels(0,0,Gdx.graphics.getBackBufferWidth()*3/4,Gdx.graphics.getBackBufferHeight(),true);
        for(int i = 4; i < pixels.length; i += 4) {
            pixels[i - 1] = (byte) 255;
        }

        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth()*3/4, Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);

        return pixmap;
    }

}