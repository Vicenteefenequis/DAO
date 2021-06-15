package view;

import model.dao.CursoDAO;
import model.dao.DisciplinaDAO;
import model.dao.FactoryDAO;
import model.entities.Disciplina;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TelaDisciplina {

    static DisciplinaDAO disciplinaDAO = FactoryDAO.createDisciplinaDAO();

    @SuppressWarnings("resource")
    public static Scanner menuDisciplina(Scanner console) throws InterruptedException, ParseException {

        int opcao = 0;
        do {
            System.out.println("\n\n");
            System.out.println("    ###   Tela: Disciplina     ###");
            System.out.println("    =========================");
            System.out.println("    |     1 - Cadastrar             |");
            System.out.println("    |     2 - Localizar             |");
            System.out.println("    |     3 - Listar                |");
            System.out.println("    |     4 - Alterar               |");
            System.out.println("    |     5 - Apagar                |");
            System.out.println("    |     0 - Retornar              |");
            System.out.println("    =========================");
            System.out.print("    Op��o -> ");
            opcao = console.nextInt();
            console.nextLine();

            switch (opcao) {
                case 1:
                    console = cadastrar(console);
                    break;
                case 2:
                    console = localizar(console);
                    break;
                case 3:
                    console = listar(console);
                    break;
                case 4:
                    console = alterar(console);
                    break;
                case 5:
                    console = apagar(console);
                    break;
                case 0:
                    console = TelaPrincipal.menuPrincipal(console);
                    break;
                default:
                    System.out.println("Op��o inv�lida!");
                    TimeUnit.SECONDS.sleep(1);
            }
        } while (opcao != 0);
        return console;
    }

    private static Scanner cadastrar(Scanner console) throws ParseException {

        Disciplina c = new Disciplina();

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Cadastrar ###");
        System.out.println("    =========================");

        System.out.print("    |     Nome Disciplina: ");
        c.setNomeDisciplina(console.nextLine());

        System.out.println("    =========================");

        disciplinaDAO.insert(c);

        console.nextLine();
        return console;
    }

    private static Scanner listar(Scanner console) {

        List<Disciplina> disciplinas = disciplinaDAO.findAll();

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Listar    ###");
        System.out.println("    =========================");
        System.out.println("    |     Id Disciplina\tNome Disciplina");
        for (Disciplina c : disciplinas) {
            System.out.println("    |     " + c.getIdDisciplina()
                    + "\t" + c.getNomeDisciplina());
        }
        System.out.println("    =========================");
        console.nextLine();
        return console;
    }


    private static Scanner localizar(Scanner console) {


        System.out.println("\n\n");
        System.out.println("    ###   Localizar Disciplina pelo Id   ###");
        System.out.println("    =========================");
        System.out.print("    |     Digite o Id: ");
        int id = console.nextInt();
        Disciplina d = disciplinaDAO.findById(id);

        System.out.println("    |     Id Disciplina\tNome Disciplina");
        System.out.println("    |     " + d.getIdDisciplina()
                + "\t" + d.getNomeDisciplina());


        System.out.println("    =========================");
        console.nextLine();
        return console;
    }

    private static Scanner alterar(Scanner console) throws ParseException {

        Disciplina d = new Disciplina();

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Alterar   ###");
        System.out.println("    =========================");
        System.out.print("    |     Id Disciplina: ");
        d.setIdDisciplina(console.nextInt());
        console.nextLine();

        System.out.print("    |     Nome Disciplina: ");
        d.setNomeDisciplina(console.nextLine());

        System.out.println("    =========================");
        disciplinaDAO.update(d);

        console.nextLine();
        return console;
    }

    private static Scanner apagar(Scanner console) throws ParseException {

        System.out.println("\n\n");
        System.out.println("    ###   Disciplina-Excluir   ###");
        System.out.println("    =========================");
        System.out.print("    |     Digite o Id: ");
        int id = console.nextInt();
        console.nextLine();
        System.out.println("    =========================");

        disciplinaDAO.deleteById(id);

        console.nextLine();
        return console;
    }
}
