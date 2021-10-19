package mn.stock.broker.com.danielprinz.broker.model;

import java.math.BigDecimal;

public class Quote {

    private Symbol symbol;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal lastPrice;
    private BigDecimal volume;

    public Quote(Symbol symbol, BigDecimal bid, BigDecimal ask, BigDecimal lastPrice, BigDecimal volume) {
        this.symbol = symbol;
        this.bid = bid;
        this.ask = ask;
        this.lastPrice = lastPrice;
        this.volume = volume;
    }

    public Quote() {
    }

    public static QuoteBuilder builder() {
        return new QuoteBuilder();
    }

    public Symbol getSymbol() {
        return this.symbol;
    }

    public BigDecimal getBid() {
        return this.bid;
    }

    public BigDecimal getAsk() {
        return this.ask;
    }

    public BigDecimal getLastPrice() {
        return this.lastPrice;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Quote)) return false;
        final Quote other = (Quote) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        if (this$symbol == null ? other$symbol != null : !this$symbol.equals(other$symbol)) return false;
        final Object this$bid = this.getBid();
        final Object other$bid = other.getBid();
        if (this$bid == null ? other$bid != null : !this$bid.equals(other$bid)) return false;
        final Object this$ask = this.getAsk();
        final Object other$ask = other.getAsk();
        if (this$ask == null ? other$ask != null : !this$ask.equals(other$ask)) return false;
        final Object this$lastPrice = this.getLastPrice();
        final Object other$lastPrice = other.getLastPrice();
        if (this$lastPrice == null ? other$lastPrice != null : !this$lastPrice.equals(other$lastPrice)) return false;
        final Object this$volume = this.getVolume();
        final Object other$volume = other.getVolume();
        if (this$volume == null ? other$volume != null : !this$volume.equals(other$volume)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Quote;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $symbol = this.getSymbol();
        result = result * PRIME + ($symbol == null ? 43 : $symbol.hashCode());
        final Object $bid = this.getBid();
        result = result * PRIME + ($bid == null ? 43 : $bid.hashCode());
        final Object $ask = this.getAsk();
        result = result * PRIME + ($ask == null ? 43 : $ask.hashCode());
        final Object $lastPrice = this.getLastPrice();
        result = result * PRIME + ($lastPrice == null ? 43 : $lastPrice.hashCode());
        final Object $volume = this.getVolume();
        result = result * PRIME + ($volume == null ? 43 : $volume.hashCode());
        return result;
    }

    public String toString() {
        return "Quote(symbol=" + this.getSymbol() + ", bid=" + this.getBid() + ", ask=" + this.getAsk() + ", lastPrice=" + this.getLastPrice() + ", volume=" + this.getVolume() + ")";
    }

    public static class QuoteBuilder {
        private Symbol symbol;
        private BigDecimal bid;
        private BigDecimal ask;
        private BigDecimal lastPrice;
        private BigDecimal volume;

        QuoteBuilder() {
        }

        public QuoteBuilder symbol(Symbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public QuoteBuilder bid(BigDecimal bid) {
            this.bid = bid;
            return this;
        }

        public QuoteBuilder ask(BigDecimal ask) {
            this.ask = ask;
            return this;
        }

        public QuoteBuilder lastPrice(BigDecimal lastPrice) {
            this.lastPrice = lastPrice;
            return this;
        }

        public QuoteBuilder volume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public Quote build() {
            return new Quote(symbol, bid, ask, lastPrice, volume);
        }

        public String toString() {
            return "Quote.QuoteBuilder(symbol=" + this.symbol + ", bid=" + this.bid + ", ask=" + this.ask + ", lastPrice=" + this.lastPrice + ", volume=" + this.volume + ")";
        }
    }
}
