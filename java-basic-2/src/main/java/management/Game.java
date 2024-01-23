package management;

public class Game implements GameMBean {
    private String playerName;

    public Game(String playerName) {
        this.playerName = playerName;
    }

    public Game() {}


    @Override
    public void playFootball(String clubName) {
        System.out.println(this.playerName + " playing for " + clubName);
    }

    @Override
    public String getPlayerName() {
        System.out.println("Return playerName " + this.playerName);
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        System.out.println("Set playerName to value " + playerName);
        this.playerName = playerName;
    }
}
