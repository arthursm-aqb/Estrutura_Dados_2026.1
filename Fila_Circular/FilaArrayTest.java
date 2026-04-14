import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilaArrayTest {

    private FilaArray fila;

    @BeforeEach
    public void setUp() {
        // Inicializa a fila com capacidade reduzida (3) para induzir a execução das 
        // rotinas de redimensionamento (expansão e contração) com menor número de operações.
        fila = new FilaArray(3);
    }

    // ==========================================
    // 1. VALIDAÇÃO DO ESTADO VAZIO E LANÇAMENTO DE EXCEÇÕES
    // ==========================================

    @Test
    public void testExcecoesFilaRecemCriada() {
        assertTrue(fila.isEmpty());
        assertEquals(0, fila.size());

        assertThrows(EFilaVazia.class, () -> fila.first(), "Deve lançar EFilaVazia ao solicitar first() em fila recém-instanciada");
        assertThrows(EFilaVazia.class, () -> fila.end(), "Deve lançar EFilaVazia ao solicitar end() em fila recém-instanciada");
        assertThrows(EFilaVazia.class, () -> fila.dequeue(), "Deve lançar EFilaVazia ao invocar dequeue() em fila recém-instanciada");
    }

    @Test
    public void testExcecoesAposEsvaziarFila() throws EFilaVazia {
        fila.enqueue("A");
        fila.enqueue("B");
        fila.dequeue();
        fila.dequeue();

        assertTrue(fila.isEmpty());
        assertThrows(EFilaVazia.class, () -> fila.dequeue(), "Deve lançar EFilaVazia após a remoção de todos os elementos");
    }

    // ==========================================
    // 2. VALIDAÇÃO DO COMPORTAMENTO CIRCULAR E REDIMENSIONAMENTO DINÂMICO
    // ==========================================

    @Test
    public void testCrescimentoComPonteirosDeslocados() throws EFilaVazia {
        // Desloca os índices 'first' e 'last' para posições intermediárias do arranjo interno.
        fila.enqueue("A");
        fila.enqueue("B");
        fila.dequeue(); 
        fila.enqueue("C");
        fila.enqueue("D"); 
        // Estado atual: tamanho = 3, capacidade = 3. Fila em capacidade máxima. Estrutura interna: [D, B, C].

        // A inserção subsequente acionará o método de expansão do arranjo com os índices fora das extremidades iniciais.
        fila.enqueue("E"); 

        assertEquals(4, fila.size());
        assertEquals("B", fila.first());
        assertEquals("E", fila.end());
    }

    @Test
    public void testEncolhimentoComPonteirosDeslocados() throws EFilaVazia {
        // Força a expansão da capacidade interna da fila para 6.
        for (int i = 0; i < 4; i++) fila.enqueue(i);
        
        // Remove elementos para acionar a rotina de contração (tamanho <= capacidade/3), atingindo a condição 2 <= 6/3.
        fila.dequeue();
        fila.dequeue(); 
        
        // Verifica se a capacidade foi reduzida à metade e se os índices foram realinhados na nova estrutura.
        assertEquals(2, fila.size());
        assertEquals(2, fila.first());
        assertEquals(3, fila.end());
    }

    // ==========================================
    // 3. VALIDAÇÃO DA LÓGICA DE INVERSÃO (MÉTODO REVERSE)
    // ==========================================

    @Test
    public void testInversaoBasica() throws EFilaVazia {
        fila.enqueue("P1");
        fila.enqueue("P2");
        fila.enqueue("P3");

        fila.reverse();

        assertEquals("P3", fila.first(), "O primeiro elemento após a inversão deve corresponder ao último inserido");
        assertEquals("P1", fila.end(), "O último elemento após a inversão deve corresponder ao primeiro inserido");
        
        assertEquals("P3", fila.dequeue());
        assertEquals("P2", fila.dequeue());
        assertEquals("P1", fila.dequeue());
        assertTrue(fila.isEmpty());
    }

    @Test
    public void testInversoesConsecutivasAnulamEfeito() throws EFilaVazia {
        fila.enqueue(1);
        fila.enqueue(2);
        
        fila.reverse();
        fila.reverse(); // O estado lógico da fila deve ser restaurado à ordem original de inserção.

        assertEquals(1, fila.first());
        assertEquals(2, fila.end());
    }

    // ==========================================
    // 4. VALIDAÇÃO DE CENÁRIOS COMPLEXOS (REDIMENSIONAMENTO COM INVERSÃO ATIVA)
    // ==========================================

    @Test
    public void testCrescimentoEnquantoFilaEstaInvertida() throws EFilaVazia {
        fila.enqueue("A");
        fila.enqueue("B");
        fila.enqueue("C"); // A fila atinge sua capacidade máxima inicial (3).

        fila.reverse(); // A ordem lógica dos elementos passa a ser: C -> B -> A.

        // A inserção acionará a duplicação da capacidade (para 6). Com a flag 'reverso' ativa, 
        // o processo de redimensionamento deve realinhar fisicamente o arranjo interno na 
        // nova ordem lógica (C, B, A, D) e redefinir a flag 'reverso' para falso.
        fila.enqueue("D"); 

        assertEquals(4, fila.size());
        assertEquals("C", fila.first(), "A ordem lógica deve ser preservada após o redimensionamento");
        assertEquals("D", fila.end(), "O novo elemento deve ser posicionado no final lógico da fila");
        
        // Remove todos os elementos para validar a sequência lógica de saída.
        assertEquals("C", fila.dequeue());
        assertEquals("B", fila.dequeue());
        assertEquals("A", fila.dequeue());
        assertEquals("D", fila.dequeue());
    }

    @Test
    public void testEncolhimentoEnquantoFilaEstaInvertida() throws EFilaVazia {
        // Induz a expansão da capacidade interna para 6.
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.enqueue(4);

        fila.reverse(); // Ordem lógica esperada: 4 -> 3 -> 2 -> 1.

        // A remoção de elementos reduzirá o tamanho para 2 (condição <= 6/3), acionando a contração do arranjo.
        assertEquals(4, fila.dequeue());
        assertEquals(3, fila.dequeue());

        // Após a contração, a estrutura interna deve ter redefinido a flag 'reverso' para falso, 
        // linearizando os elementos restantes (2 -> 1).
        assertEquals(2, fila.size());
        assertEquals(2, fila.first());
        assertEquals(1, fila.end());
    }
}