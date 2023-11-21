package adapter;


import adapter.Converter;

public class USDEUROConverter implements Converter{
    private float rate = (float) 1.1;
    @Override
    public float convert(float amount) {
       return amount/rate;
    }
}
