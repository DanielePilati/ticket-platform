package org.web.app.java.spring.platform.ticket;

public class Prova {

	public static void main(String[] args) {
	
		int[] array = {1,5,3,4,6};
		int sum = 0;
		for(int i = 0 ; i < array.length ; i++) {
			sum = sum + array[i];
		}
		int media = sum / array.length;
		System.out.println("la tua somma è " + sum);
		System.out.println("la tua media è " + media);

	}

}
