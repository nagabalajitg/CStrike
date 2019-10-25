package com.strike.clean.coin;

public interface Coin 
{
    public static enum CoinType
    {
        BLACK(1),RED(2),STRIKER(3);

        int coinType;
        private CoinType(int coinType)
        {
            this.coinType = coinType;
        }

        public int getCoinType()
        {
            return coinType;
        }

        public static CoinType getCoin(int coinType)
        {
            for (CoinType type : CoinType.values())
            {
                if(type.getCoinType() == coinType)
                {
                    return type;
                }
            }

            throw new IllegalStateException();
        }
    }

    public CoinType getCoinType();
    public int getCoinNumber();
}
