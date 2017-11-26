package com.androidcurso.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture [] passaros;
	private Texture fundo;
	private Texture canobaixo;
	private Texture canotopo;
	private Texture gameOver;
	private Random numerico_randomico;
	private BitmapFont fonte;
	private BitmapFont mensagen;
	private Circle passaroCirculo;
	private Rectangle retangulocanoalto;
	private Rectangle retangulocanoBaixo;
	//private ShapeRenderer shape;
	private int pontuacao = 0;

	//Atributos de configuracao
	private float largura_dispositivo;
	private float altura_dispositivo;
	private int estado_do_jogo =0;

	private float variacao = 0;
	private float velocidadeQueda= 0;
	private float posicaoinicialvertical;
	private float posicaomovimentocanohorizontal;
	private float espaco_entre_canos;
	private float deltatime;
	private float alturaentrecanosrandomica;
    private boolean marcouponto = false;

	//Camera
	private OrthographicCamera camera;
	private Viewport viewport;
	private  final float VIRTUAL_WIDTH = 768;
	private  final float VIRTUAL_HEIGHT = 1024;

	//Criando aplicacao

	@Override
	public void create () {

		batch = new SpriteBatch();
		numerico_randomico = new Random();
		passaroCirculo = new Circle();
		/*retangulocanoBaixo = new Rectangle();
		retangulocanoalto = new Rectangle();
		shape = new ShapeRenderer();*/
		canobaixo = new Texture("cano_baixo.png");
		canotopo  = new Texture("cano_topo.png");

		fonte= new BitmapFont();
		fonte.setColor(Color.WHITE);;
		fonte.getData().setScale(6);

		mensagen = new BitmapFont();

		mensagen.setColor(Color.WHITE);
		mensagen.getData().setScale(3);

		passaros = new Texture[3];
		passaros [0] = new Texture("passaro1.png");
		passaros [1] = new Texture("passaro2.png");
		passaros [2] = new Texture("passaro3.png");
		gameOver = new Texture("game_over.png");
		fundo = new Texture("fundo.png");

		//configuracoes da camera
		camera = new OrthographicCamera();
		camera.position.set(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2,0);
		viewport = new  StretchViewport(VIRTUAL_WIDTH,VIRTUAL_HEIGHT,camera);

		largura_dispositivo = VIRTUAL_WIDTH;
		altura_dispositivo = VIRTUAL_HEIGHT;
		posicaoinicialvertical = altura_dispositivo /2;
		posicaomovimentocanohorizontal  = largura_dispositivo  ;
		espaco_entre_canos = 400;
	}

	//
	@Override
	public void render () {

		camera.update();

		//limpar fremes anteriores;
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |GL20.GL_DEPTH_BUFFER_BIT);
		deltatime =  Gdx.graphics.getDeltaTime();
		variacao+= deltatime *10;
		if (variacao > 2) variacao =0;

		if(estado_do_jogo == 0 ){
			if (Gdx.input.justTouched()){
				estado_do_jogo = 1;

			}



		}
		//estado iniciado
		else {

			velocidadeQueda++;
			if (posicaoinicialvertical >0 || velocidadeQueda < 0)
				posicaoinicialvertical = posicaoinicialvertical - velocidadeQueda;



			if (estado_do_jogo == 1){
				posicaomovimentocanohorizontal -= deltatime * 200;

				if (Gdx.input.justTouched())	{
					velocidadeQueda = -10;
				}

				//verifica se o cano saiu da tela

				if (posicaomovimentocanohorizontal <- canotopo.getWidth()){
					posicaomovimentocanohorizontal = largura_dispositivo ;
					alturaentrecanosrandomica = numerico_randomico.nextInt(400)-200;
					marcouponto = false;
				}

				//verifica pontuacao
				if(posicaomovimentocanohorizontal < 120){
					if (!marcouponto){
						pontuacao++;
						marcouponto = true;
					}
				}

			}else {//Tela de Game over

				if (Gdx.input.justTouched()){
					estado_do_jogo = 0;
					pontuacao = 0;
					velocidadeQueda = 0;
					posicaoinicialvertical = altura_dispositivo / 2;
					posicaomovimentocanohorizontal = largura_dispositivo;


				}


			}

		}


		//dados de projecao da camera
		batch.setProjectionMatrix(camera.combined);


		batch.begin();

		batch.draw(fundo,0,0,largura_dispositivo,altura_dispositivo);
		batch.draw(canotopo,posicaomovimentocanohorizontal,altura_dispositivo / 2 + espaco_entre_canos /2 +alturaentrecanosrandomica);
		batch.draw(canobaixo,posicaomovimentocanohorizontal,altura_dispositivo / 2 -canobaixo.getHeight()- espaco_entre_canos / 2+ alturaentrecanosrandomica	);
		batch.draw(passaros[(int)variacao],120,posicaoinicialvertical);
		fonte.draw(batch,String.valueOf(pontuacao),largura_dispositivo / 2,altura_dispositivo - 50 	);
		if (estado_do_jogo == 2){

			batch.draw(gameOver,largura_dispositivo / 2 - gameOver.getWidth() / 2,altura_dispositivo / 2);
			mensagen.draw(batch,"Toque para Reiniciar",largura_dispositivo / 2 - 200, altura_dispositivo / 2- gameOver.getHeight() / 2);


		}
		batch.end();

		passaroCirculo.set(120+ passaros[0].getWidth() /2 ,posicaoinicialvertical + passaros[0].getWidth()/2, passaros[0].getWidth() / 2);
		retangulocanoBaixo = new Rectangle(posicaomovimentocanohorizontal,altura_dispositivo / 2 -canobaixo.getHeight()- espaco_entre_canos / 2+ alturaentrecanosrandomica,
				canobaixo.getWidth(),canobaixo.getHeight());
		retangulocanoalto =  new Rectangle(posicaomovimentocanohorizontal,altura_dispositivo / 2 + espaco_entre_canos /2 +alturaentrecanosrandomica,canotopo.getWidth(),
		canotopo.getHeight());

		//Desenhar formas
	/*		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.circle(passaroCirculo.x,passaroCirculo.y,passaroCirculo.radius);
		shape.rect(retangulocanoBaixo.x,retangulocanoBaixo.y,retangulocanoBaixo.width,retangulocanoBaixo.height);
		shape.rect(retangulocanoalto.x,retangulocanoalto.y,retangulocanoalto.getWidth(),retangulocanoalto.getHeight());
		shape.setColor(Color.RED);
		shape.end();*/



		if(Intersector.overlaps(passaroCirculo,retangulocanoBaixo) ||Intersector.overlaps(passaroCirculo,retangulocanoalto) ||
		 posicaoinicialvertical <= 0 || posicaoinicialvertical >= altura_dispositivo){

			estado_do_jogo= 2;


		}

	}

	//


	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
