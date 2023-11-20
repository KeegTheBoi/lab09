package it.unibo.mvc.view;


import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberConsoleView implements DrawNumberView {

    private static final String INIT_GAME_LOG = "Start Game!";
    private static final String NEW_GAME = ": a new game starts!";

    public DrawNumberConsoleView(){}

    @Override
    public void setController(DrawNumberController observer) {}

    @Override
    public void start() {
        System.out.println(INIT_GAME_LOG);
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                plainMessage(res.getDescription());
                return;
            }
            case YOU_WON -> plainMessage(res.getDescription() + NEW_GAME);
            case YOU_LOST -> plainMessage(res.getDescription() + NEW_GAME);
            default -> throw new IllegalStateException("Unknown game state");
        }
    }

    private void plainMessage(final String msg) {
        System.out.println(msg);
    }
    
}
