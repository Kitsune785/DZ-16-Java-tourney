package org.example.src.tournament;

import org.example.src.domain.NotRegisteredException;
import org.example.src.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    private Game game = new Game();
    private Player player1 = new Player(1, "Vika", 1);
    private Player player2 = new Player(2, "Eva", 2);
    private Player player3 = new Player(3, "Anya", 3);
    private Player player4 = new Player(4, "Kolya", 3);

    @BeforeEach
    void setUp() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
    }

    @Test
    void shouldFindRegisteredPlayers() {
        assertEquals(List.of(player1, player2, player3, player4), game.findAll());
    }

    @Test
    void shouldThrowExceptionWhenPlayer1NotRegister() {
        assertThrows(NotRegisteredException.class, () -> game.round("Petya", "Kolya"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer2NotRegister() {
        assertThrows(NotRegisteredException.class, () -> game.round("Eva", "Valera"));
    }

    @Test
    void shouldThrowExceptionWhenTwoPlayersNotRegister() {
        assertThrows(NotRegisteredException.class, () -> game.round("Valera", "Petya"));
    }

    @Test
    void shouldFindByName() {
        assertEquals(player3, game.findByName("Anya"));
    }

    @Test
    void shouldNotFindByName() {
        assertEquals(null, game.findByName("Vasya"));
    }

    @Test
    void shouldWinTheFirstOne() {
        int actual = game.round("Kolya", "Eva");
        assertEquals(1, actual);
    }

    @Test
    void shouldWinTheSecondOne() {
        int actual = game.round("Eva", "Anya");
        assertEquals(2, actual);
    }

    @Test
    void shouldBeDraw() {
        int actual = game.round("Anya", "Kolya");
        assertEquals(0, actual);
    }
}
