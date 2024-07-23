package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ClienteServiceTest.class,
    ClienteDAOTest.class,
    ProdutoServiceTest.class,
    ProdutoDAOTest.class,
    VendaDAOTest.class
})
public class AllTests {
    // Esta classe permanecerá vazia, ela é usada apenas como um contêiner para os testes acima
}
