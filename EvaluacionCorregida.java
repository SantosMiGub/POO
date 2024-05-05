package pruebas;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
/**package pruebas;

 */
public class EvaluacionCorregida {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[][] productos = new int[50][3];
		int contProductos = 0;
		int opcion;
		
		contProductos = ingresarProducto(scanner, productos, contProductos, true);
		
		do {
			opcion = mostrarMenu(scanner);
			contProductos = generarAccion(opcion, scanner, productos, contProductos);
		}while(opcion != 3);
		
		scanner.close();
	}
	
	public static int mostrarMenu(Scanner scanner) {
		int opcion = 0;
		System.out.println("Ingrese la opcion: ");
		System.out.println("1 - Consultar Producto");
		System.out.println("2 - Dar de alta un producto");
		System.out.println("3 - Salir");
		opcion = validarEntero(scanner, 3, 1);
		return opcion;
	}
	
	
	public static int ingresarProducto(Scanner scanner , int[][] productos, int contProductos, boolean primero) {
		do {
			if(primero) {
				System.out.println("Ingrese el codigo del producto");
				productos[contProductos][0] = validarCodigo(scanner, productos, contProductos, true);
			}
			else {
				System.out.println("Ingrese el codigo del producto");
				productos[contProductos][0] = validarCodigo(scanner, productos, contProductos, false);
			}
			
		
			if(productos[contProductos][0] != 0) {
				System.out.println("Ingrese el precio del producto");
				productos[contProductos][1] = validarEntero(scanner, 10000, 1);
		
				System.out.println("Ingrese la cantidad del producto");
				productos[contProductos][2] = validarEntero(scanner, 10000, 1);
		}

		
			contProductos++;
		}while(contProductos < productos.length && productos[contProductos-1][0] != 0 && primero);
		
		return contProductos;
	}
	
	public static int generarAccion(int opcion, Scanner scanner, int[][] productos, int contProductos) {
		switch(opcion) {
			case 1:
				consultarProducto(productos, contProductos, scanner);
				break;
			case 2: 
				contProductos = ingresarProducto(scanner, productos, contProductos, false);
				break;
			case 3:
				System.out.println("Esta saliendo del sistema");
				break;
		}
		return contProductos;
	}
	
	public static void consultarProducto(int[][] productos, int contProductos, Scanner scanner) {
		System.out.println("Ingrese el codigo del productos a consultar");
		int Consulta = validarEntero(scanner, 500, 1);
		int indice = buscarExistencia(Consulta, productos, contProductos);
		if(indice != -1) {
			System.out.println("Codigo: " + productos[indice][0]);
			System.out.println("Precio: " + productos[indice][1]);
			System.out.println("Cantidad: " + productos[indice][2]);	
		}
		else {
			System.out.println("No se a encontrado el producto");
		}

		
	}
	
	public static int validarCodigo(Scanner scanner, int[][] productos, int contProductos, boolean primero) {
		int codigo;
		int existe = 0;

		do {
			existe = -1;
			if(primero) {
				codigo = validarEntero(scanner, 500, 0);
			}
			else {
				codigo = validarEntero(scanner, 500, 1);
			}
				
			if(contProductos != 0) {
			existe = buscarExistencia(codigo, productos, contProductos);
			}	
		
		}while(existe != -1);
		
		return codigo;
	}
	
	public static int buscarExistencia(int codigo, int[][] productos, int contProductos) {
		int i = 0;
		int existe = -1;
		do {
			if(codigo == productos[i][0]) {
				existe = i;
				System.out.println("Se a encontrado el codigo");
			}
			i++;
		}while((i < contProductos) && existe == -1);
		return existe;
	}
	
	public static int validarEntero(Scanner scanner, int maximo, int minimo) {
		int entero = 0;
		boolean error;
		do {
			error = false;
			try {
				entero = scanner.nextInt();
				if(entero > maximo || entero < minimo) {
					System.out.println("Error. Ingrese un entero dentro del rango " + maximo + " y " +  minimo);
					scanner.nextLine();
					error = true;
				}
			}catch(InputMismatchException Entero) {
				System.out.println("Error. Ingrese valores enteros");
				scanner.nextLine();
				error = true;
			}catch(Exception General) {
				System.out.println("Error. No identificado");
				error = true;
			}
		}while(error);
		return entero;
	}
	
	
	
    
    
}
