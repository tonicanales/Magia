package com.example.ejb.bussines;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.example.ejb.domain.Carta;
import com.example.ejb.domain.Game;

@Stateful
@LocalBean
	
	public class Greeting {

	private int cont = 0;
	private ArrayList<ArrayList<Carta>> mazo3x7 = new ArrayList<ArrayList<Carta>>();
	private Carta carta;
	Game game = new Game();

	public Greeting(){
	}
	
	
	
	public void jugar(int column){
		if (cont == 1){
			mazo3x7 = game.startGame(0);
		} else if (cont==2 || cont==3){
			mazo3x7 = game.nextBaraja(column);
		}
	}	
		
	
	//public void ultimacarta(int column){
	//	carta = game.cartafinal(column, mazo3x7);
	//}
		
	public void ultimacarta(int column){
		carta = game.cartafinal(column);
	}
	

	public ArrayList<ArrayList<Carta>> getMazo3x7() {
		return mazo3x7;
	}



	public void setMazo3x7(ArrayList<ArrayList<Carta>> mazo3x7) {
		this.mazo3x7 = mazo3x7;
	}



	public Carta getCarta() {
		return carta;
	}



	public void setCarta(Carta carta) {
		this.carta = carta;
	}



	public int getCont() {
		return cont;
	}

	public void resetCont(){
		cont = 0;
	}

	public void doCont() {
		cont++;
	}
	
	

			
}
