package application;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import entities.Funcionario;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Registrar quantos funcionários? ");
		int nFunc = sc.nextInt();
		sc.nextLine();
		List<Funcionario> lista = new ArrayList<>();

		for (int i = 1; i <= nFunc; i++) {
			System.out.println("Funcionario " + i + ": ");
			System.out.print("Id: ");
			int id = sc.nextInt();

			while (temId(lista, id)) {
				System.out.println("Id já cadastrado, digite outro: ");
				id = sc.nextInt();
			}
			sc.nextLine();

			System.out.print("Nome: ");
			String nome = sc.nextLine();
			System.out.print("Salário: $ ");
			double salario = sc.nextDouble();
			System.out.println();
			lista.add(new Funcionario(id, nome, salario));
		}
		listarFuncionarios(lista);
		
		System.out.print("ID do funcionário a aumentar o salário: ");
		int id = sc.nextInt();
		Funcionario func = lista.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if(func != null) {
			System.out.print("Valor percentual a aumentar: ");
			double perc = sc.nextDouble();
			func.aumentarSalario(perc);
		} else {
			System.out.println("Id não encontrado!");
		}
		
		listarFuncionarios(lista);
		sc.close();
	}

	public static boolean temId(List<Funcionario> listaF, int idF) {
		Funcionario func = listaF.stream().filter(x -> x.getId() == idF).findFirst().orElse(null);
		return func != null;
	}
	
	public static void listarFuncionarios(List<Funcionario> listaF) {
		System.out.println();
		System.out.println("List of employees:");
		for (Funcionario obj : listaF) {
			System.out.println(obj);
		}
	}
}
