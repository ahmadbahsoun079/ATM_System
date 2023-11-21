package adapter;


import adapter.Converter;

public class CurrencyConverter {
    private Converter converter;
    public CurrencyConverter(Converter converter){
        this.converter = converter;
    }
    public float performConversion(float amount){
        return converter.convert(amount);
    }
}
