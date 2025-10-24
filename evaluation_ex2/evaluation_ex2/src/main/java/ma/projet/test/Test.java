package ma.projet.test;

import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        EmployeService employeService = context.getBean(EmployeService.class);
        EmployeTacheService employeTacheService = context.getBean(EmployeTacheService.class);
        ProjetService projetService = context.getBean(ProjetService.class);
        TacheService tacheService = context.getBean(TacheService.class);


        Employe emp1 = new Employe();
        emp1.setNom("hajar");
        emp1.setPrenom("syassi");
        employeService.create(emp1);

        Employe emp2 = new Employe();
        emp2.setNom("adam");
        emp2.setPrenom("ayoub");
        employeService.create(emp2);


        Projet proj1 = new Projet();
        proj1.setNom("Projet A");
        proj1.setChef(emp1);
        proj1.setDateDebut(LocalDate.of(2025, 9, 1));
        proj1.setDateFin(LocalDate.of(2025, 12, 31));
        projetService.create(proj1);

        Projet proj2 = new Projet();
        proj2.setNom("Projet B");
        proj2.setChef(emp2);
        proj2.setDateDebut(LocalDate.of(2025, 8, 15));
        proj2.setDateFin(LocalDate.of(2025, 11, 30));
        projetService.create(proj2);


        Tache t1 = new Tache();
        t1.setNom("Tâche Planifiée 1");
        t1.setProjet(proj1);
        t1.setPrix(500f);
        t1.setDateDebut(LocalDate.of(2025, 9, 5));
        t1.setDateFin(null); // pas encore réalisée
        tacheService.create(t1);

        Tache t2 = new Tache();
        t2.setNom("Tâche Planifiée 2");
        t2.setProjet(proj2);
        t2.setPrix(800f);
        t2.setDateDebut(LocalDate.of(2025, 8, 20));
        t2.setDateFin(null);
        tacheService.create(t2);


        Tache t3 = new Tache();
        t3.setNom("Tâche Réalisée 1");
        t3.setProjet(proj1);
        t3.setPrix(1500f);
        t3.setDateDebut(LocalDate.of(2025, 9, 10));
        t3.setDateFin(LocalDate.of(2025, 10, 1));
        tacheService.create(t3);

        Tache t4 = new Tache();
        t4.setNom("Tâche Réalisée 2");
        t4.setProjet(proj1);
        t4.setPrix(2000f);
        t4.setDateDebut(LocalDate.of(2025, 10, 2));
        t4.setDateFin(LocalDate.of(2025, 10, 5));
        tacheService.create(t4);


        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(emp1);
        et1.setTache(t1);
        employeTacheService.create(et1);

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(emp1);
        et2.setTache(t3);
        employeTacheService.create(et2);

        EmployeTache et3 = new EmployeTache();
        et3.setEmploye(emp2);
        et3.setTache(t2);
        employeTacheService.create(et3);

        EmployeTache et4 = new EmployeTache();
        et4.setEmploye(emp1);
        et4.setTache(t4);
        employeTacheService.create(et4);


        System.out.println("\n--- Tâches d'un employé ---");
        employeService.afficherTachesParEmploye(emp1.getId());

        System.out.println("\n--- Projets gérés par l'employé ---");
        employeService.afficherProjetsGeresParEmploye(emp1.getId());

        System.out.println("\n--- Tâches planifiées pour le projet ---");
        projetService.afficherTachesPlanifieesParProjet(proj1.getId());

        System.out.println("\n--- Tâches réalisées pour le projet ---");
        projetService.afficherTachesRealiseesParProjet(proj1.getId());

        System.out.println("\n--- Tâches prix > 1000 ---");
        tacheService.afficherTachesPrixSuperieurA1000();

        System.out.println("\n--- Tâches réalisées entre deux dates ---");
        LocalDate debut = LocalDate.of(2025, 9, 1);
        LocalDate fin = LocalDate.of(2025, 10, 10);
        tacheService.afficherTachesEntreDates(debut, fin);

        context.close();
    }
}
