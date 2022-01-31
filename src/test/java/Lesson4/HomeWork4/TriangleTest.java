package Lesson4.HomeWork4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Lesson4.HomeWork4.Triangle.TriangleSquareGerone;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {

    private static Logger logger = LoggerFactory.getLogger("TriangleTest.class");

    @BeforeAll
    static void beforeAll() {
        //System.out.println("Выполнимся 1 раз перед всеми тестами, например загрузка данных в базу!");
        //TRACE, DEBUG, INFO, ERROR
        logger.info("log data");
        logger.trace("trace log data");
        logger.error("err log data");
    }

    @BeforeEach
    void beforeEach() {
        //System.out.println("Выполняемся перед каждым тестом");
    }

    @Test
    @DisplayName("Тест подсчета площади треугольника")
    void givenCorrectSquareResultWhenRunTriangleSquareGeroneMethod() throws TriangleException {
        double result = TriangleSquareGerone(2,3,4);
        assertEquals(2.904738,result, 0.000001);
    }

    @ParameterizedTest
    @DisplayName("Параметризированный тест подсчета площади треугольника")
    @CsvSource({"10, 5, 8, 19.810035", "5, 10, 6, 11.399013", "2, 2, 2, 1.732051"})
    void givenCorrectResultSquareWhenRunTriangleSquareGeroneMethod(int a, int b, int c, double result) throws TriangleException {
        assertEquals(result, TriangleSquareGerone(a,b,c), 0.000001);
    }

    @Test
    @DisplayName("Тест подсчета площади вырожденного треугольника")
    void givenCorrectSquareResultWhenRunTriangleSquareGeroneMetodWhenSquareIsNull() throws TriangleException {
        double result = TriangleSquareGerone(1,2,3);
        assertEquals(0,result);
    }

    @Test
    @DisplayName("Тест проверки возникновения исключения при некорректных положительных значениях сторон треугольника")
    void exceptionWhenRunTriangleSquareGeroneMetodWhenIncorrectPositiveSides(){
        assertThrows(TriangleException.class, () -> TriangleSquareGerone(10,2,2));
    }

    @Test
    @DisplayName("Тест проверки возникновения исключения при минусовых и нулевых значениях сторон треугольника")
    void exceptionWhenRunTriangleSquareGeroneMetodWhenNegativeAndNullSides(){
        assertAll(
                () -> assertThrows(TriangleException.class, () -> TriangleSquareGerone(-10,2,2)),
                () -> assertThrows(TriangleException.class, () -> TriangleSquareGerone(10,-2,2)),
                () -> assertThrows(TriangleException.class, () -> TriangleSquareGerone(10,2,-2)),
                () -> assertThrows(TriangleException.class, () -> TriangleSquareGerone(0,2,2)),
                () -> assertThrows(TriangleException.class, () -> TriangleSquareGerone(10,0,2)),
                () -> assertThrows(TriangleException.class, () -> TriangleSquareGerone(10,2,0))
        );
    }

    @Test
    @DisplayName("Тест проверки площади треугольника c помощью библиотеки AssertJ")
    void AssertJTest () {
        assertAll(
                () -> assertThat(TriangleSquareGerone(10,5,10)).isEqualTo(24.206145913796355),
                () -> assertThat(TriangleSquareGerone(10,5,10)).isGreaterThan(24).isLessThan(25)
        );
    }

    @AfterEach
    void afterEach() {
        //System.out.println("Закрытие браузера");
    }

    @AfterAll
    static void tearDown() {
        //System.out.println("Метод 1 раз выполнится после всех тестов!");
    }
}
