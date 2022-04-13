package app.testes;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Aluno;

public class TesteJPAaluno {

	public static void main(String[] args) {
		// Cria um aluno.
		Aluno aluno = new Aluno();
		aluno.setId(1L);
		aluno.setNome("Rafael Augusto");
		aluno.setDtnasc(new Date(90, 1, 2));
		
		EntityManager em = getEntityManager();
		em.getTransaction().begin();  // inicia a transacao para incluir novos alunos
		// salva no banco de dados
		em.persist(aluno);
         
        
		// Cria um novo aluno.
		aluno = new Aluno();
		aluno.setId(2L);
		aluno.setNome("Sandra Maria");
		aluno.setDtnasc(new Date(80, 11, 1));
		em.persist(aluno);

		// finaliza a transacao e commita os dados no banco.
		em.getTransaction().commit();
		
		
		// exibe os dados salvos na tabela Aluno		
		List<Aluno> todosAlunos = em.createQuery("SELECT a from Aluno a").getResultList();
		System.out.println("Alunos cadastrados - " + todosAlunos.size());
		for (Aluno a: todosAlunos) {
			System.out.println("Aluno: "+ a.getNome()
			     + " DtNasc: " + a.getDtnasc().toLocaleString()
			     + " Idade(anos): " + a.getIdade()
			);
		}
				
		// fecha a conexao e o entity-manager
		em.close();
	}

	private static EntityManager getEntityManager() {
		// Obtem a fabrica(factory) de gestor de entidades
		// conectado pela unidade de persistencia.
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-exemplos-UP");

		// Cria um entity manager (o gestor de entidades)
		EntityManager entityManager = factory.createEntityManager();

		System.out.println("OK. Conectado!");
		return entityManager;
	}

}
