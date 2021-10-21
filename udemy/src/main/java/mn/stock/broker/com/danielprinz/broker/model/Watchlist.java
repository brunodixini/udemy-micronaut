package mn.stock.broker.com.danielprinz.broker.model;

import java.util.ArrayList;
import java.util.List;

public class Watchlist {

    private List<Symbol> symbols = new ArrayList<>();

    public Watchlist(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public Watchlist() {
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Watchlist)) return false;
        final Watchlist other = (Watchlist) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$symbols = this.getSymbols();
        final Object other$symbols = other.getSymbols();
        if (this$symbols == null ? other$symbols != null : !this$symbols.equals(other$symbols)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Watchlist;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $symbols = this.getSymbols();
        result = result * PRIME + ($symbols == null ? 43 : $symbols.hashCode());
        return result;
    }

    public String toString() {
        return "Watchlist(symbols=" + this.getSymbols() + ")";
    }
}
