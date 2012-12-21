package control;

import control.Tools.glPlayer;
import control.Tools.Tool;
import control.Tools.glPoint;
import control.Worldly.World;
import control.People.NPC;
import control.Misc.InGameDate;
import control.Worldly.Environment;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import glapp.*;
import java.util.*;

/**
 * Use setMaterial(), setLight() and makeTexture() to control light and material properties.
 * <P>
 * napier at potatoland dot org
 */

public class GLApp_LandSmash extends GLApp {
    public static boolean isTimePassing=true;
    // Handles for textures
    int[] TextureHandle;
    // Light position: if last value is 0, then this describes light direction.  If 1, then light position.
    float lightPosition1[]= { 0f, 0f, 0f, 1f };
    float lightPosition2[]= { 0f, -10f, 0f, 0f };
    float lightPosition3[]= { 0f, 0f, 0f, 1f };
    // Rotation of sphere
    float rotation=0f;
    // display lists
    
    char cDown = '!';
    char cUp = '!';
    float yLook = 0f;
    glPoint vP=new glPoint();
    glPoint vL=new glPoint();
    
    GLFont font;
    int sphereDL=0, cubeDL=0;
    
    public static World WORLD = new World();
    public static boolean running=true;
    
    boolean OnMove=true;
    //------------------------------------------------------------------------
    // Run main loop of application.  Handle mouse and keyboard input.
    //------------------------------------------------------------------------

    public static void main(String args[]) {        
    	GLApp_LandSmash demo = new GLApp_LandSmash();
        demo.window_title = "GLApp Simple Scene";
        demo.displayWidth = 800;
        demo.displayHeight = 600;
        demo.run();  // will call init(), render(), mouse event functions
    }

    public boolean initDisplayTESTTEMP() {
       
        // Initialize the Window
        try {
        	DM = displayMode = new DisplayMode(1440,930);
        	fullScreen = false;
            Display.create(new PixelFormat(0, depthBufferBits, 8));  // set bits per buffer: alpha, depth, stencil
            Display.setTitle(window_title);
            Display.setFullscreen(fullScreen);
            Display.setVSyncEnabled(VSyncEnabled);
            Display.setLocation(0,-30);
            msg("GLApp.initDisplay(): Created OpenGL window.");
        }
        catch (Exception exception1) {
            System.err.println("GLApp.initDisplay(): Failed to create OpenGL window: " + exception1);
            System.exit(1);
        }
        // Set viewport width/height and Aspect ratio.
        if (aspectRatio == 0f) {
            // no aspect ratio was set in GLApp.cfg: default to full screen.
            aspectRatio = (float)DM.getWidth() / (float)DM.getHeight(); // calc aspect ratio of display
        }
        // viewport may not match shape of screen.  Adjust to fit.
        viewportH = DM.getHeight();                        // viewport Height
        viewportW = (int) (DM.getHeight() * aspectRatio);  // Width
        if (viewportW > DM.getWidth()) {
            viewportW = DM.getWidth();
            viewportH = (int) (DM.getWidth() * (1 / aspectRatio));
        }
        // center viewport in screen
        viewportX = (int) ((DM.getWidth()-viewportW) / 2);
        viewportY = (int) ((DM.getHeight()-viewportH) / 2);
        return true;
    }

    public void setup()
    {
        // Create sphere texture
        TextureHandle=new int[30];
        //TextureHandle[0]=(makeTexture("images/clear.png"));
        WORLD.Create();
//        STICK1.makeRect(new glPoint(0.0f,0,1.0f));
//        STICK2.makeRect(new glPoint(0.0f,-1.0f,0.0f));
//        STICK2.Intersect(STICK1);
//        STICK2.Inject((glPoint)STICK1.mPoints.get(4));
//        STICK1.Intersect(STICK2);
//        System.out.println(STICK1.mInters.size());
        
//        PLAYER.setup();
        setSight();
    }
    void setSight()
    {
        vP.set(2, 15f);
        // setup perspective
        setPerspective();
        // no overall scene lighting
        setAmbientLight(new float[] { 2.0f, 2.0f, 2.0f, 2.0f });

        sphereDL = beginDisplayList();
        	renderSphere();
        endDisplayList();
        
        cubeDL = beginDisplayList();
        	renderCube(10,40);
        endDisplayList();
        
        font = new GLFont( new Font("Trebuchet", Font.PLAIN, 14) );
        
        // enable lighting and texture rendering
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // select model view for subsequent transforms
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    /**
     * Set the camera position, field of view, depth.
     */
    public static void setPerspective()
    {
        // select projection matrix (controls perspective)
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        // fovy, aspect ratio, zNear, zFar
        GLU.gluPerspective(30f, aspectRatio, 1f, 100f);
        // return to modelview matrix
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }


    /**
     * Render one frame.  Called by GLApp.run().
     */
    int stage=0;
    float bsinc=0;
    float nvinc=0;
    String inputSTR=new String();
    public void update()
    {
    }
    public void draw() {
        
        
        // clear depth buffer and color buffers
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // select model view for subsequent transforms
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        // set the viewpoint
        GLU.gluLookAt((float)vP.get(0),(float) vP.get(1), (float)vP.get(2),  // where is the eye
        			(float)vL.get(0), (float)vL.get(1), (float)vL.get(2),   // what point are we looking at
        			0f, 1f, 0f);    // which way is up

       
        //---------------------------------------------------------------
        // shiny blue ball
        //---------------------------------------------------------------
        
        // shiny dark blue material
        setMaterial( new float[] {1.0f, 1.0f, 1.0f, 1.0f}, 1.0f); 

        // set blue light at same spot as blue oxygen
        setLightPosition( GL11.GL_LIGHT3, lightPosition3 );
        
        setMaterial( new float[] {1.0f, 1.0f, 1.0f, 1.0f}, 1.0f);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
        if(stage>=0) WORLD.draw();
    }


    public void mouseMove(int x, int y) {
        /*
        PLAYER.ponx=x/20;
        PLAYER.pony=y/16;
        if(!PLAYER.OnAddColour){PLAYER.ponx=x/30;
        PLAYER.pony=y/25;
        if(PLAYER.pony>=24)PLAYER.pony=23;
        if(PLAYER.ponx>=16)PLAYER.ponx=15;}
        */
//        System.out.println(PLAYER.ponx+","+PLAYER.pony);
    }
    public void cleanup(){
        WORLD.destory();
        super.cleanup();
    }
    public void mouseWheel(int wheelMoved) {
        /*
        switch(stage)
        {
            case 6:
            {
            if(cDown=='z'){PLAYER.colourhueset.pos[0]+=wheelMoved/120;
        if(PLAYER.colourhueset.pos[0]<0)PLAYER.colourhueset.pos[0]=360;
        if(PLAYER.colourhueset.pos[0]>360)PLAYER.colourhueset.pos[0]=0;}
        if(cDown=='x'){PLAYER.colourhueset.pos[1]+=(float)wheelMoved/12000*PLAYER.colourhueset.typ[0];
            if(PLAYER.colourhueset.pos[1]>1)PLAYER.colourhueset.typ[0]=-1;
            if(PLAYER.colourhueset.pos[1]<0)PLAYER.colourhueset.typ[0]=1;}
        if(cDown=='c'){
            PLAYER.colourhueset.pos[2]+=(float)wheelMoved/12000*PLAYER.colourhueset.typ[1];
            if(PLAYER.colourhueset.pos[2]>1)PLAYER.colourhueset.typ[1]=-1;
            if(PLAYER.colourhueset.pos[2]<0)PLAYER.colourhueset.typ[1]=1;}
        if(cDown=='b'){bsinc+=wheelMoved/120;}
        if(cDown=='n'){nvinc+=wheelMoved/120;}
        break;
        }
        }
         * 
         */
    
    }
    public void mouseLDown(int x, int y) {/*
        switch(stage)
        {
            case 6:
            {
        if(PLAYER.OnAddColour)
        {
            if(cDown!='v')PLAYER.mAppear[PLAYER.pony][PLAYER.ponx]=new glPoint(PLAYER.colourhueset.hue());
            else PLAYER.colourhueset=new glPoint(PLAYER.mAppear[PLAYER.pony][PLAYER.ponx].convertRGBhue(),1,1,1);
        }
        else {PLAYER.colourhueset=new glPoint(PLAYER.colourchoice[23-PLAYER.pony][15-PLAYER.ponx]);
//        System.out.println(PLAYER.colourhueset.pos[2]);
        }
        break;
            }
        }
        */
    }
    public void mouseRDown(int x, int y) {
        //if(cDown!='v'){if(PLAYER.OnAddColour)PLAYER.mAppear[PLAYER.pony][PLAYER.ponx]=new glPoint();}
        //else PLAYER.deleteCOLOUR(PLAYER.mAppear[PLAYER.pony][PLAYER.ponx]);
    }

    public void mouseLUp(int x, int y) {
        
    }


    public void keyDown(int keycode) {
        switch (keycode)
        {
            case 2:{cDown='1';break;}
            case 3:{cDown='2';break;}
            case 4:{cDown='3';break;}
            case 5:{cDown='4';break;}
            case 6:{cDown='5';break;}
            case 7:{cDown='6';break;}
            case 8:{cDown='7';break;}
            case 9:{cDown='8';break;}
            case 10:{cDown='9';break;}
            case 11:{cDown='0';break;}
            case 12:{cDown='-';break;}
            case 13:{cDown='=';break;}
            
            case 28:{cDown='\n';break;}
            case 14:{cDown='<';break;}
            case 15:{cDown='\t';break;}
            case 208:{cDown='W';if(OnMove)vL.minus(1);break;}//Up
            case 200:{cDown='S';if(OnMove)vL.add(1);break;} //Down
            case 205:{cDown='D';if(OnMove)vL.add(0);break;}//Right
            case 203:{cDown='A';if(OnMove)vL.minus(0);break;} //Left
            case 42:{cDown='^';break;}
            case 29:{cDown='&';OnMove=false;break;} 
            case 54:{cDown='^';break;}
            case 157:{cDown='&';break;} 
            case 184:{cDown='*';break;}  
            case 56:{cDown='*';break;}
            case 57:{cDown=' ';break;}     
               
            case 16:{cDown='q';if(OnMove){vP.minus(1);vL.minus(1);}else{
                WORLD.zoomIn(WORLD.getZoomed().getNPC(0).getVantage());
            }break;}
            case 17:{cDown='w';if(OnMove){vP.minus(2);vL.minus(2);}break;}
            case 18:{cDown='e';if(OnMove){vP.add(1);vL.add(1);}break;}
            case 19:{cDown='r';break;}
            case 20:{cDown='t';break;}
            case 21:{cDown='y';break;}
            case 22:{cDown='u';break;}
            case 23:{cDown='i';break;}
            case 24:{cDown='o';break;}
            case 25:{cDown='p';break;}
            case 26:{cDown='[';break;}
            case 27:{cDown=']';break;}
            
            case 30:{cDown='a';if(OnMove){vP.minus(0);vL.minus(0);}break;}
            case 31:{cDown='s';if(OnMove){vP.add(2);vL.add(2);}break;}
            case 32:{cDown='d';if(OnMove){vP.add(0);vL.add(0);}break;}
            case 33:{cDown='f';break;}
            case 34:{cDown='g';break;}
            case 35:{cDown='h';break;}
            case 36:{cDown='j';break;}
            case 37:{cDown='k';break;}
            case 38:{cDown='l';break;}
            case 39:{cDown=';';break;}
            case 40:{cDown='\'';break;}
            case 41:{cDown='\\';break;}
                
            case 44:{cDown='z';break;}
            case 45:{cDown='x';break;}
            case 46:{cDown='c';break;}
            case 47:{cDown='v';break;}
            case 48:{cDown='b';break;}
            case 49:{cDown='n';break;}
            case 50:{cDown='m';break;}
            case 51:{cDown=',';break;}
            case 52:{cDown='.';}
            case 53:{cDown='/';break;}
                
            default:
            {
                cDown='!';
                break;
            }
        }
        //System.out.println(keycode);
    }
    public void keyUp(int keycode) {
        cDown='!';
        switch (keycode)
        {
            case 2:{cUp='1';}
            case 3:{cUp='2';}
            case 4:{cUp='3';}
            case 5:{cUp='4';}
            case 6:{cUp='5';}
            case 7:{cUp='6';}
            case 8:{cUp='7';}
            case 9:{cUp='8';}
            case 10:{cUp='9';}
            case 11:{cUp='0';}
            case 12:{cUp='-';}
            case 13:{cUp='=';}
            
            case 28:{cUp='\n';}
            case 15:{cUp='\t';}
            case 208:{cUp='#';}
            case 200:{cUp='@';} 
            case 42:{cUp='^';}
            case 29:{cUp='&';} 
            case 54:{cUp='^';}
            case 157:{cUp='&';OnMove=true;} 
            case 184:{cUp='*';}  
            case 56:{cUp='*';}
            case 57:{cUp=' ';}     
               
            case 16:{cUp='q';}
            case 17:{cUp='w';}
            case 18:{cUp='e';}
            case 19:{cUp='r';}
            case 20:{cUp='t';}
            case 21:{cUp='y';}
            case 22:{cUp='u';}
            case 23:{cUp='i';}
            case 24:{cUp='o';}
            case 25:{cUp='p';}
            case 26:{cUp='[';}
            case 27:{cUp=']';}
            
            case 30:{cUp='a';}
            case 31:{cUp='s';}
            case 32:{cUp='d';}
            case 33:{cUp='f';}
            case 34:{cUp='g';}
            case 35:{cUp='h';}
            case 36:{cUp='j';}
            case 37:{cUp='k';}
            case 38:{cUp='l';}
            case 39:{cUp=';';}
            case 40:{cUp='\'';}
            case 41:{cUp='\\';}
                
            case 44:{cUp='z';}
            case 45:{cUp='x';}
            case 46:{cUp='c';}
            case 47:{cUp='v';}
            case 48:{cUp='b';}
            case 49:{cUp='n';}
            case 50:{cUp='m';}
            case 51:{cUp=',';}
            case 52:{cUp='.';}
            case 53:{cUp='/';}
                
            default:
            {
                cUp='!';
            }
        }
        
    }
    public static InGameDate DATE=new InGameDate();
    
}