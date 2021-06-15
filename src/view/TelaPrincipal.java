package view;

import java.text.ParseException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TelaPrincipal {

	public static Scanner menuPrincipal(Scanner console) throws ParseException, InterruptedException {
		
		int opcao = 0;
		do {
			System.out.println("\n\n### Sistema de Controle Escolar  ###");
			System.out.println("    =========================");
			System.out.println("    |     1 - Aluno         |");
			System.out.println("    |     2 - Curso         |");
			System.out.println("    |     3 - Disciplina    |");
			System.out.println("    |     0 - Sair          |");
			System.out.println("    =========================");
			System.out.print("    Op��o -> ");
			opcao = console.nextInt();
			console.nextLine();
			
			switch (opcao) {
				case 1: return TelaAluno.menuAluno(console);
				case 2: return TelaCurso.menuCurso(console);
				case 3: return TelaDisciplina.menuDisciplina(console);
				case 0: System.out.println("    Aplica��o foi encerrada!");
						break;
				default:
					System.out.println("Op��o Inv�lida!");
					TimeUnit.SECONDS.sleep(1);
			}
		} while(opcao!=0);
		return console;
	}
}